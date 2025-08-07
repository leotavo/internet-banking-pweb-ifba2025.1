package com.banco.bankapi.repository;

import com.banco.bankapi.model.Operacao;
import com.banco.bankapi.model.TipoOperacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

    // Extrato do usuário logado
    Page<Operacao> findByConta_Usuario_IdAndDataBetween(
            Long usuarioId, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);

    Page<Operacao> findByConta_Usuario_IdAndTipoAndDataBetween(
            Long usuarioId, TipoOperacao tipo, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
}
