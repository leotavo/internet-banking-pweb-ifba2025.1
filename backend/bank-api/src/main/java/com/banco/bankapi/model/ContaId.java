package com.banco.bankapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ContaId implements Serializable {

    @NotBlank(message = "Número da conta é obrigatório")
    @Pattern(regexp = "\\d{6}", message = "Número da conta deve ter 6 dígitos")
    @Column(name = "numero", nullable = false, length = 6)
    private String numero;

    @NotBlank(message = "Agência é obrigatória")
    @Pattern(regexp = "\\d{4}", message = "Agência deve ter 4 dígitos")
    @Column(name = "agencia", nullable = false, length = 4)
    private String agencia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaId)) return false;
        ContaId that = (ContaId) o;
        return Objects.equals(numero, that.numero) && Objects.equals(agencia, that.agencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, agencia);
    }
}
