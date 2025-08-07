package com.banco.bankapi.controller;

import com.banco.bankapi.dto.OperacaoDTO;
import com.banco.bankapi.service.OperacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operacoes")
public class OperacaoController {

    private final OperacaoService operacaoService;

    public OperacaoController(OperacaoService operacaoService) {
        this.operacaoService = operacaoService;
    }

    @PostMapping
    public ResponseEntity<OperacaoDTO> realizarOperacao(@RequestBody OperacaoDTO dto) {
        return ResponseEntity.ok(operacaoService.realizarOperacao(dto));
    }

    @GetMapping
    public ResponseEntity<List<OperacaoDTO>> listarOperacoes() {
        return ResponseEntity.ok(operacaoService.listarTodas());
    }
}
