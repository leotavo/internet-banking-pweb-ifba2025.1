package com.banco.bankapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @Column(unique = true, length = 11)
    private String cpf;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senhaHash;

    private LocalDateTime dataCadastro = LocalDateTime.now();

    // Relacionamento 1:1 com Conta (chave composta)
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Conta conta;

    // Perfis de acesso
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuarios_roles",
        joinColumns = @JoinColumn(name = "usuarios_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private List<Role> roles = new ArrayList<>();

    // Métodos exigidos por UserDetails (Spring Security)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senhaHash;
    }

    @Override
    public String getUsername() {
        return email; // ou cpf, se preferir autenticar por CPF
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
