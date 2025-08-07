package com.banco.bankapi.service;

import com.banco.bankapi.dto.ContaIdDTO;
import com.banco.bankapi.dto.OperacaoDTO;
import com.banco.bankapi.model.TipoOperacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OperacaoService {

    // a) Depósito em conta destino
    OperacaoDTO deposito(ContaIdDTO contaDestino, BigDecimal valor, String descricaoOpcional);

    // b) Saque do usuário logado
    OperacaoDTO saque(Long usuarioId, BigDecimal valor);

    // c) Pagamento do usuário logado
    OperacaoDTO pagamento(Long usuarioId, BigDecimal valor, String descricao);

    // d) Extrato do usuário logado (com filtros)
    Page<OperacaoDTO> extrato(Long usuarioId, TipoOperacao tipo, LocalDate de, LocalDate ate, Pageable pageable);
}
