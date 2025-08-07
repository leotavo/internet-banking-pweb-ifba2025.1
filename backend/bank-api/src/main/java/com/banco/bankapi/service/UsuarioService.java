package com.banco.bankapi.service;

import com.banco.bankapi.dto.UsuarioDTO;
import com.banco.bankapi.form.UsuarioForm;

public interface UsuarioService {
    UsuarioDTO criar(UsuarioForm form);
    UsuarioDTO buscarPorId(Long id);
}
