package com.banco.bankapi.controller;

import com.banco.bankapi.dto.ContaIdDTO;
import com.banco.bankapi.dto.OperacaoDTO;
import com.banco.bankapi.model.TipoOperacao;
import com.banco.bankapi.service.OperacaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/operacoes", produces = "application/json")
public class OperacaoController {

    private final OperacaoService operacaoService;

    public OperacaoController(OperacaoService operacaoService) {
        this.operacaoService = operacaoService;
    }

    // a) Depósito — credita na conta destino indicada
    @PostMapping(value = "/deposito", consumes = "application/json")
    public ResponseEntity<OperacaoDTO> deposito(@Valid @RequestBody DepositoRequest req) {
        OperacaoDTO dto = operacaoService.deposito(
                new ContaIdDTO(req.getAgencia(), req.getNumero()),
                req.getValor(),
                req.getDescricao()
        );
        return ResponseEntity.ok(dto);
    }

    // b) Saque — debita da conta do usuário logado (X-User-Id)
    @PostMapping(value = "/saque", consumes = "application/json")
    public ResponseEntity<OperacaoDTO> saque(
            @RequestHeader("X-User-Id") Long usuarioId,
            @Valid @RequestBody SaqueRequest req) {
        return ResponseEntity.ok(operacaoService.saque(usuarioId, req.getValor()));
    }

    // c) Pagamento — debita da conta do usuário logado (X-User-Id)
    @PostMapping(value = "/pagamento", consumes = "application/json")
    public ResponseEntity<OperacaoDTO> pagamento(
            @RequestHeader("X-User-Id") Long usuarioId,
            @Valid @RequestBody PagamentoRequest req) {
        return ResponseEntity.ok(operacaoService.pagamento(usuarioId, req.getValor(), req.getDescricao()));
    }

    // d) Extrato do usuário logado com filtros (tipo, período) e paginação
    @GetMapping("/me")
    public ResponseEntity<Page<OperacaoDTO>> extrato(
            @RequestHeader("X-User-Id") Long usuarioId,
            @RequestParam(required = false) TipoOperacao tipo,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate de,
            @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate ate,
            Pageable pageable) {
        return ResponseEntity.ok(operacaoService.extrato(usuarioId, tipo, de, ate, pageable));
    }

    // ===== Requests (DTOs de entrada) =====

    public static class DepositoRequest {
        @NotBlank private String agencia;
        @NotBlank private String numero;
        @NotNull @Positive private BigDecimal valor;
        private String descricao;

        public String getAgencia() { return agencia; }
        public void setAgencia(String agencia) { this.agencia = agencia; }
        public String getNumero() { return numero; }
        public void setNumero(String numero) { this.numero = numero; }
        public BigDecimal getValor() { return valor; }
        public void setValor(BigDecimal valor) { this.valor = valor; }
        public String getDescricao() { return descricao; }
        public void setDescricao(String descricao) { this.descricao = descricao; }
    }

    public static class SaqueRequest {
        @NotNull @Positive private BigDecimal valor;
        public BigDecimal getValor() { return valor; }
        public void setValor(BigDecimal valor) { this.valor = valor; }
    }

    public static class PagamentoRequest {
        @NotNull @Positive private BigDecimal valor;
        @NotBlank private String descricao;

        public BigDecimal getValor() { return valor; }
        public void setValor(BigDecimal valor) { this.valor = valor; }
        public String getDescricao() { return descricao; }
        public void setDescricao(String descricao) { this.descricao = descricao; }
    }
}
