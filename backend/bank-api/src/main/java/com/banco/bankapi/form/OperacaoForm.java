// ============================
// OperacaoForm.java
// ============================
package com.banco.bankapi.form;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import com.banco.bankapi.model.TipoOperacao;
import com.banco.bankapi.dto.ContaIdDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperacaoForm {
    @NotNull(message = "Tipo da operação é obrigatório")
    private TipoOperacao tipo;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal valor;

    private String descricao;

    @NotNull(message = "Conta é obrigatória")
    private ContaIdDTO conta;
}