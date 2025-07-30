package com.banco.bankapi.repository;

import com.banco.bankapi.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
    List<Operacao> findByContaIdAgenciaAndContaIdNumero(String agencia, String numero);
}
