package com.projeto.desafio_java.repositorios;

import com.projeto.desafio_java.entidades.ItemPedido;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends BaseRepository<ItemPedido, UUID> {
    public BigDecimal calcularValorTotalProdutosNoPedido(UUID idPedido);
    public List<ItemPedido> buscarTodosPeloPedido(UUID idPedido);
    public boolean possuiPedido(UUID idProduto);
}
