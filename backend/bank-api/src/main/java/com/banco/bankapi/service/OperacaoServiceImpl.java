package com.banco.bankapi.service;

import com.banco.bankapi.dto.ContaIdDTO;
import com.banco.bankapi.dto.OperacaoDTO;
import com.banco.bankapi.model.*;
import com.banco.bankapi.repository.ContaRepository;
import com.banco.bankapi.repository.OperacaoRepository;
import com.banco.bankapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional
public class OperacaoServiceImpl implements OperacaoService {

    private final OperacaoRepository operacaoRepository;
    private final ContaRepository contaRepository;
    private final UsuarioRepository usuarioRepository;

    // a) Depósito
    @Override
    public OperacaoDTO deposito(ContaIdDTO contaDestino, BigDecimal valor, String descricaoOpcional) {
        validarValorPositivo(valor);

        Conta conta = contaRepository.findById(new ContaId(contaDestino.getNumero(), contaDestino.getAgencia()))
                .orElseThrow(() -> new IllegalArgumentException("Conta destino não encontrada."));

        // credita
        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepository.save(conta);

        Operacao op = new Operacao();
        op.setTipo(TipoOperacao.DEPOSITO);
        op.setValor(valor);
        op.setDescricao(descricaoOpcional);
        op.setData(LocalDateTime.now());
        op.setConta(conta);
        op = operacaoRepository.save(op);

        enviarEmail(conta.getUsuario().getEmail(),
                "Depósito realizado",
                "Depósito de R$ " + valor + " em sua conta " + conta.getId().getAgencia() + "/" + conta.getId().getNumero());

        return toDTO(op);
    }

    // b) Saque (usuario logado)
    @Override
    public OperacaoDTO saque(Long usuarioId, BigDecimal valor) {
        validarValorPositivo(valor);

        Conta conta = obterContaDoUsuario(usuarioId);
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para saque.");
        }

        // debita
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);

        Operacao op = new Operacao();
        op.setTipo(TipoOperacao.SAQUE);
        op.setValor(valor);
        op.setDescricao("Saque");
        op.setData(LocalDateTime.now());
        op.setConta(conta);
        op = operacaoRepository.save(op);

        enviarEmail(conta.getUsuario().getEmail(),
                "Saque realizado",
                "Saque de R$ " + valor + " em sua conta " + conta.getId().getAgencia() + "/" + conta.getId().getNumero());

        return toDTO(op);
    }

    // c) Pagamento (usuario logado)
    @Override
    public OperacaoDTO pagamento(Long usuarioId, BigDecimal valor, String descricao) {
        validarValorPositivo(valor);
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição é obrigatória para pagamento.");
        }

        Conta conta = obterContaDoUsuario(usuarioId);
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para pagamento.");
        }

        // debita
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);

        Operacao op = new Operacao();
        op.setTipo(TipoOperacao.PAGAMENTO);
        op.setValor(valor);
        op.setDescricao(descricao);
        op.setData(LocalDateTime.now());
        op.setConta(conta);
        op = operacaoRepository.save(op);

        enviarEmail(conta.getUsuario().getEmail(),
                "Pagamento realizado",
                "Pagamento de R$ " + valor + " - " + descricao);

        return toDTO(op);
    }

    // d) Extrato (usuario logado) com filtros
    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public Page<OperacaoDTO> extrato(Long usuarioId, TipoOperacao tipo, LocalDate de, LocalDate ate, Pageable pageable) {
        LocalDateTime inicio = (de != null) ? de.atStartOfDay() : LocalDate.of(1970,1,1).atStartOfDay();
        LocalDateTime fim    = (ate != null) ? ate.atTime(LocalTime.MAX) : LocalDateTime.now();

        Page<Operacao> page = (tipo != null)
                ? operacaoRepository.findByConta_Usuario_IdAndTipoAndDataBetween(usuarioId, tipo, inicio, fim, pageable)
                : operacaoRepository.findByConta_Usuario_IdAndDataBetween(usuarioId, inicio, fim, pageable);

        return page.map(this::toDTO);
    }

    // ===== Helpers =====

    private Conta obterContaDoUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + usuarioId));
        return contaRepository.findByUsuario(usuario)
                .orElseThrow(() -> new IllegalStateException("Conta não encontrada para usuário " + usuarioId));
    }

    private void validarValorPositivo(BigDecimal valor) {
        if (valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo.");
        }
    }

    private OperacaoDTO toDTO(Operacao op) {
        return new OperacaoDTO(
                op.getId(),
                op.getTipo(),
                op.getValor(),
                op.getDescricao(),
                op.getData(),
                new ContaIdDTO(
                        op.getConta().getId().getAgencia(),
                        op.getConta().getId().getNumero()
                )
        );
    }

    // Stub: integre com seu e-mail-service aqui (WebClient/Feign/etc.)
    private void enviarEmail(String para, String assunto, String corpo) {
        // TODO integrar com microserviço de e-mail
        // por enquanto, noop/log.
    }
}
