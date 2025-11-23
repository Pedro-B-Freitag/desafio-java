package com.projeto.desafio_java.controllers;

import com.projeto.desafio_java.dtos.ProdutoServicoDto;
import com.projeto.desafio_java.entidades.ProdutoServico;
import com.projeto.desafio_java.servicos.ProdutoServicoService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/produtosServicos")
public class ProdutoServicoController extends BaseController<ProdutoServico, UUID, ProdutoServicoDto> {

    protected ProdutoServicoController(ProdutoServicoService produtoServicoService) {
        super(produtoServicoService);
    }

}
