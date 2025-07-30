package com.banco.bankapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "contas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Conta {

    @EmbeddedId
    private ContaId id;

    @NotNull
    private BigDecimal saldo = BigDecimal.ZERO;

    // Relacionamento 1:1 com Usuario
    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relacionamento 1:N com Operacao
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operacao> operacoes = new ArrayList<>();
}
