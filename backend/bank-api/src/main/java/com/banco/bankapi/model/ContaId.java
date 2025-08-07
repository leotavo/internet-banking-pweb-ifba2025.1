package com.banco.bankapi.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaId implements Serializable {

    @NotBlank(message = "Número da conta é obrigatório")
    @Pattern(regexp = "\\d{6}", message = "Número da conta deve ter 6 dígitos")
    private String numero;

    @NotBlank(message = "Agência é obrigatória")
    @Pattern(regexp = "\\d{4}", message = "Agência deve ter 4 dígitos")
    private String agencia;
}