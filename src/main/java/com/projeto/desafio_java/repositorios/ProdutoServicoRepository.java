package com.projeto.desafio_java.repositorios;

import com.projeto.desafio_java.entidades.ProdutoServico;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoServicoRepository extends BaseRepository<ProdutoServico, UUID> {
}
