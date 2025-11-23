package com.projeto.desafio_java.controllers;

import com.projeto.desafio_java.entidades.BaseEntity;
import com.projeto.desafio_java.exceptions.NotFoundException;
import com.projeto.desafio_java.servicos.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public abstract class BaseController<T extends BaseEntity, ID, DTO> {

    protected final BaseService<T, ID, DTO> servico;

    protected BaseController(BaseService<T, ID, DTO> service) {
        this.servico = service;
    }

    @GetMapping
    public List<T> listar(Pageable pageable, @RequestParam(required = false) Map<String, Object> filtros) {
        return servico.listar(pageable, filtros);
    }

    @GetMapping("/{id}")
    public T buscarPorId(@PathVariable ID id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    public T criar(@RequestBody DTO dto) {
        return servico.criar(dto);
    }

    @PutMapping("/{id}")
    public T atualizar(@PathVariable ID id, @RequestBody DTO dto) {
        return servico.salvar(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable ID id) {
        servico.deletar(id);
    }
}

