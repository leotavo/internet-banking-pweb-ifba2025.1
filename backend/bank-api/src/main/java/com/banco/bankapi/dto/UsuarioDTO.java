// ============================
// UsuarioDTO.java (resposta)
// ============================
package com.banco.bankapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String dataCadastro;
}