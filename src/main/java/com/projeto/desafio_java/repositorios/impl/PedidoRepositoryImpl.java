package com.projeto.desafio_java.repositorios.impl;

import com.projeto.desafio_java.entidades.Pedido;
import com.projeto.desafio_java.entidades.QPedido;
import com.projeto.desafio_java.repositorios.PedidoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PedidoRepositoryImpl extends BaseRepositoryImpl<Pedido, UUID> implements PedidoRepository {

    protected final QPedido pedido = QPedido.pedido;

    public PedidoRepositoryImpl(EntityManager em) {
        super(Pedido.class, em);
    }
}
