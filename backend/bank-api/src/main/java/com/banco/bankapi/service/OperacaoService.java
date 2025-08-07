package com.banco.bankapi.service;

import com.banco.bankapi.dto.OperacaoDTO;
import java.util.List;

public interface OperacaoService {
    OperacaoDTO realizarOperacao(OperacaoDTO dto);
    List<OperacaoDTO> listarTodas();
}
