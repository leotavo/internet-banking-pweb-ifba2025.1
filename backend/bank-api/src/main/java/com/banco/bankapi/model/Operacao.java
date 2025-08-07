package com.banco.bankapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "operacoes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Operacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Positive
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoOperacao tipo;

    @Size(max = 255)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime data = LocalDateTime.now();

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "agencia", referencedColumnName = "agencia", nullable = false),
        @JoinColumn(name = "numero",  referencedColumnName = "numero",  nullable = false)
    })
    private Conta conta;

    @AssertTrue(message = "Descrição é obrigatória para operações do tipo PAGAMENTO")
    public boolean isDescricaoValida() {
        return tipo != TipoOperacao.PAGAMENTO || (descricao != null && !descricao.isBlank());
    }
}
