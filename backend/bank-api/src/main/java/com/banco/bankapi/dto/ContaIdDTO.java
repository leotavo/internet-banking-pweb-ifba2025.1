// ============================
// ContaIdDTO.java
// ============================
package com.banco.bankapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaIdDTO {
    @NotBlank(message = "Agência é obrigatória")
    private String agencia;

    @NotBlank(message = "Número da conta é obrigatório")
    private String numero;
}