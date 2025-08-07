package com.banco.bankapi.controller;

import com.banco.bankapi.dto.UsuarioDTO;
import com.banco.bankapi.form.UsuarioForm;
import com.banco.bankapi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/usuarios", produces = "application/json")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // @CrossOrigin(origins = "*") // habilite se precisar pro frontend
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UsuarioDTO> criarUsuario(@Valid @RequestBody UsuarioForm form) {
        UsuarioDTO dto = usuarioService.criar(form);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }
}
