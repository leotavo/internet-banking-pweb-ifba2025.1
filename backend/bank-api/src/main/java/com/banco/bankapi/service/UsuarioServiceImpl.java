package com.banco.bankapi.service;

import com.banco.bankapi.dto.UsuarioDTO;
import com.banco.bankapi.form.UsuarioForm;
import com.banco.bankapi.model.Conta;
import com.banco.bankapi.model.ContaId;
import com.banco.bankapi.model.Usuario;
import com.banco.bankapi.repository.ContaRepository;
import com.banco.bankapi.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ContaRepository contaRepository;

    @Override
    public UsuarioDTO criar(UsuarioForm form) {
        // Mapear form -> entidade
        Usuario usuario = new Usuario();
        usuario.setNome(form.getNome());
        usuario.setCpf(form.getCpf());
        usuario.setEmail(form.getEmail());

        // Hash da senha sem @Bean (apenas get/set)
        String hash = new BCryptPasswordEncoder(12).encode(form.getSenha());
        usuario.setSenhaHash(hash);

        try {
            usuario = usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            // Provável violação de UNIQUE (cpf/email)
            throw new IllegalArgumentException("CPF ou e-mail já cadastrados.", e);
        }

        // Criar conta com ID composto (agência/número) vindos do form
        Conta conta = new Conta();
        ContaId contaId = new ContaId();
        contaId.setAgencia(form.getAgencia());
        contaId.setNumero(form.getNumeroConta());
        conta.setId(contaId);
        conta.setUsuario(usuario);
        contaRepository.save(conta);

        return toDTO(usuario);
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));
        return toDTO(usuario);
    }

    // Mapper simples entidade -> DTO
    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }
}
