package com.banco.bankapi.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Conta {

    @EmbeddedId
    @Valid
    @NotNull(message = "ID da conta (agência e número) é obrigatório")
    @AttributeOverrides({
        @AttributeOverride(name = "agencia", column = @Column(name = "agencia", nullable = false, length = 4)),
        @AttributeOverride(name = "numero",  column = @Column(name = "numero",  nullable = false, length = 6))
    })
    private ContaId id;

    @NotNull(message = "Saldo é obrigatório")
    @PositiveOrZero(message = "Saldo não pode ser negativo")
    @Column(nullable = false)
    private BigDecimal saldo = BigDecimal.ZERO;

    @NotNull(message = "Usuário é obrigatório")
    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operacao> operacoes = new ArrayList<>();
}
