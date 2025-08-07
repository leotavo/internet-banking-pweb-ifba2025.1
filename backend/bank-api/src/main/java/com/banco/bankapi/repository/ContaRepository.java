package com.banco.bankapi.repository;

import com.banco.bankapi.model.Conta;
import com.banco.bankapi.model.ContaId;
import com.banco.bankapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, ContaId> {
    Optional<Conta> findByUsuario(Usuario usuario);
}
