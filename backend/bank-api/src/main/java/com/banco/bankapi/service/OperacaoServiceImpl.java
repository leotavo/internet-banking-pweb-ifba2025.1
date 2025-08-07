package com.banco.bankapi.service;

import com.banco.bankapi.dto.OperacaoDTO;
import com.banco.bankapi.model.Operacao;
import com.banco.bankapi.model.TipoOperacao;
import com.banco.bankapi.repository.OperacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperacaoServiceImpl implements OperacaoService {

    private final OperacaoRepository repository;

    public OperacaoServiceImpl(OperacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public OperacaoDTO realizarOperacao(OperacaoDTO dto) {
        Operacao op = new Operacao();
        op.setTipo(TipoOperacao.valueOf(dto.getTipo()));
        op.setValor(dto.getValor());
        op.setDataHora(LocalDateTime.now());
        op.setDescricao(dto.getDescricao());

        op = repository.save(op);
        return new OperacaoDTO(op.getId(), op.getTipo().toString(), op.getValor(), op.getDescricao(), op.getDataHora());
    }

    @Override
    public List<OperacaoDTO> listarTodas() {
        return repository.findAll().stream().map(op ->
            new OperacaoDTO(op.getId(), op.getTipo().toString(), op.getValor(), op.getDescricao(), op.getDataHora())
        ).collect(Collectors.toList());
    }
}
