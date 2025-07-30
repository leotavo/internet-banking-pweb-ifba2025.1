package com.banco.bankapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ContaId implements Serializable {

    @NotBlank(message = "A agência é obrigatória")
    @Column(length = 4)
    private String agencia;

    @NotBlank(message = "O número da conta é obrigatório")
    @Column(length = 10)
    private String numero;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaId that)) return false;
        return Objects.equals(agencia, that.agencia) &&
               Objects.equals(numero, that.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, numero);
    }
}
