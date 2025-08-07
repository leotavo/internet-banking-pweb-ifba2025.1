package com.banco.bankapi.service;

import com.banco.bankapi.dto.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO criar(UsuarioDTO dto);
    UsuarioDTO buscarPorId(Long id);
}
