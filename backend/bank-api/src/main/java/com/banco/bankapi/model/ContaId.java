package com.banco.bankapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaId implements Serializable {

    @NotBlank(message = "Agência é obrigatória")
    @Size(min = 3, max = 10)
    private String agencia;

    @NotBlank(message = "Número da conta é obrigatório")
    @Size(min = 3, max = 10)
    private String numero;
}
