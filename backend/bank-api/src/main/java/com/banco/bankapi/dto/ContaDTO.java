package com.banco.bankapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {
    private String numero;
    private String agencia;
    private BigDecimal saldo;
    private String nomeUsuario;
}
