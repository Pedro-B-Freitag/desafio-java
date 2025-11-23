package com.projeto.desafio_java.repositorios;

import com.projeto.desafio_java.entidades.Pedido;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, UUID>{
}
