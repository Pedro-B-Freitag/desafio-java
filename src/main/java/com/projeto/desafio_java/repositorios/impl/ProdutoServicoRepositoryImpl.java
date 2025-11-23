package com.projeto.desafio_java.repositorios.impl;

import com.projeto.desafio_java.entidades.ProdutoServico;
import com.projeto.desafio_java.entidades.QProdutoServico;
import com.projeto.desafio_java.repositorios.ProdutoServicoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProdutoServicoRepositoryImpl extends BaseRepositoryImpl<ProdutoServico, UUID> implements ProdutoServicoRepository{

    protected final QProdutoServico produtoServico = QProdutoServico.produtoServico;

    public ProdutoServicoRepositoryImpl(EntityManager em) {
        super(ProdutoServico.class, em);
    }

}
