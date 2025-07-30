package com.banco.bankapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "operacoes")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Operacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O tipo da operação é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoOperacao tipo;

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    private String descricao;

    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "agencia", referencedColumnName = "agencia"),
        @JoinColumn(name = "numero", referencedColumnName = "numero")
    })
    private Conta conta;
}

