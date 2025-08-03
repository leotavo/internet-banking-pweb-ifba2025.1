package com.banco.bankapi.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "contas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @EmbeddedId
    @Valid
    @NotNull(message = "ID da conta (agência e número) é obrigatório")
    private ContaId id;

    @NotNull(message = "Saldo é obrigatório")
    @PositiveOrZero(message = "Saldo não pode ser negativo")
    private BigDecimal saldo = BigDecimal.ZERO;

    @NotNull(message = "Usuário é obrigatório")
    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operacao> operacoes = new ArrayList<>();
}
