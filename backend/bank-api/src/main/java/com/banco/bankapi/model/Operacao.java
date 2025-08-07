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

@Entity(name = "operacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "Tipo de operação é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoOperacao tipo;

    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    private String descricao;

    @NotNull(message = "Data da operação é obrigatória")
    private LocalDateTime data = LocalDateTime.now();

    @NotNull(message = "Conta associada é obrigatória")
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "agencia", referencedColumnName = "agencia"),
        @JoinColumn(name = "numero", referencedColumnName = "numero")
    })
    private Conta conta;

    
    // Validação de regra de negócio
    @AssertTrue(message = "Descrição é obrigatória para operações do tipo PAGAMENTO")
    public boolean isDescricaoValida() {
        return tipo != TipoOperacao.PAGAMENTO || (descricao != null && !descricao.isBlank());
    }
}
