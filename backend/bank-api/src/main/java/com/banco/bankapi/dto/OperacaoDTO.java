// ============================
// OperacaoDTO.java (resposta)
// ============================
package com.banco.bankapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.banco.bankapi.model.TipoOperacao;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperacaoDTO {
    private Long id;
    private TipoOperacao tipo;
    private BigDecimal valor;
    private String descricao;
    private LocalDateTime data;
    private ContaIdDTO conta;
}