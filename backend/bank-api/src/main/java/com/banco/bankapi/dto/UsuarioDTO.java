package com.banco.bankapi.dto;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;

    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e setters omitidos para brevidade
}
