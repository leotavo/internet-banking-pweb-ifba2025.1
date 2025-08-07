package com.banco.bankapi.service;

import com.banco.bankapi.dto.UsuarioDTO;
import com.banco.bankapi.model.Usuario;
import com.banco.bankapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsuarioDTO criar(UsuarioDTO dto) {
        Usuario usuario = new Usuario(null, dto.getNome(), dto.getEmail(), dto.getSenha());
        usuario = repository.save(usuario);
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
