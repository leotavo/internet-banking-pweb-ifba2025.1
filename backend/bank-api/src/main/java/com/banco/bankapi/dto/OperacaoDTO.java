package com.banco.bankapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperacaoDTO {

    private Long id;
    private String tipo;
    private BigDecimal valor;
    private String descricao;
    private LocalDateTime dataHora;

    public OperacaoDTO() {}

    public OperacaoDTO(Long id, String tipo, BigDecimal valor, String descricao, LocalDateTime dataHora) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    // Getters e setters omitidos para brevidade
}
