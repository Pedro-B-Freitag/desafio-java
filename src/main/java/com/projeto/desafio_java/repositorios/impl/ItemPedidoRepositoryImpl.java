package com.projeto.desafio_java.repositorios.impl;

import com.projeto.desafio_java.entidades.ItemPedido;
import com.projeto.desafio_java.entidades.QItemPedido;
import com.projeto.desafio_java.enums.TipoProdutoServicoEnum;
import com.projeto.desafio_java.repositorios.ItemPedidoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public class ItemPedidoRepositoryImpl extends BaseRepositoryImpl<ItemPedido, UUID> implements ItemPedidoRepository {

    protected final QItemPedido itemPedido = QItemPedido.itemPedido;

    public ItemPedidoRepositoryImpl(EntityManager em) {
        super(ItemPedido.class, em);
    }

    @Override
    public BigDecimal calcularValorTotalProdutosNoPedido(UUID idPedido) {
        BigDecimal resultado = jpaQueryFactory
                .select(itemPedido.produto.preco.multiply(itemPedido.quantidade).sum())
                .from(itemPedido)
                .where(
                        itemPedido.pedido.id.eq(idPedido)
                                .and(itemPedido.produto.tipo.eq(TipoProdutoServicoEnum.PRODUTO)
                                        .and(itemPedido.ativo.isTrue()))
                )
                .fetchFirst();
        return resultado != null ? resultado : BigDecimal.ZERO;
    }

    @Override
    public List<ItemPedido> buscarTodosPeloPedido(UUID idPedido) {
        return jpaQueryFactory
                .select(itemPedido)
                .from(itemPedido)
                .where(itemPedido.pedido.id.eq(idPedido)
                        .and(itemPedido.ativo.isTrue()))
                .fetch();
    }

    @Override
    public boolean possuiPedido(UUID idProduto) {
        return jpaQueryFactory
                .selectOne()
                .from(itemPedido)
                .where(itemPedido.produto.id.eq(idProduto)
                        .and(itemPedido.pedido.ativo.isTrue()))
                .fetchFirst() != null;
    }

    @Override
    public BigDecimal calcularValorTotalItens(UUID idPedido) {
        BigDecimal resultado = jpaQueryFactory
                .select(itemPedido.produto.preco.multiply(itemPedido.quantidade).sum())
                .from(itemPedido)
                .where(
                        itemPedido.pedido.id.eq(idPedido)
                                .and(itemPedido.ativo.isTrue())
                )
                .fetchFirst();
        return resultado != null ? resultado : BigDecimal.ZERO;
    }

}
