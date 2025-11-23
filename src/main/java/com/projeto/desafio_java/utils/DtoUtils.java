package com.projeto.desafio_java.utils;

import com.projeto.desafio_java.dtos.ItemPedidoDto;
import com.projeto.desafio_java.dtos.PedidoDto;
import com.projeto.desafio_java.dtos.ProdutoServicoDto;
import com.projeto.desafio_java.entidades.ItemPedido;
import com.projeto.desafio_java.entidades.Pedido;
import com.projeto.desafio_java.entidades.ProdutoServico;

public final class DtoUtils {

    public static ItemPedido toItemPedido(ItemPedidoDto dto, Pedido pedido, ProdutoServico produto) {
        ItemPedido item = new ItemPedido();
        return toItemPedido(dto, item, pedido, produto);
    }

    public static ItemPedido toItemPedido(ItemPedidoDto itemPedidoDto, ItemPedido itemPedido, Pedido pedido, ProdutoServico produto) {
        if(pedido != null) {
            itemPedido.setPedido(pedido);
        }
        if(produto != null) {
            itemPedido.setProduto(produto);
        }
        if(itemPedidoDto.getQuantidade() > 0){
            itemPedido.setQuantidade(itemPedidoDto.getQuantidade());
        }
        return itemPedido;
    }

    public static Pedido toPedido(PedidoDto dto) {
        Pedido pedido = new Pedido();
        return toPedido(dto, pedido);
    }

    public static Pedido toPedido(PedidoDto dto, Pedido pedido) {
        pedido.setStatus(dto.getStatus());
        return pedido;
    }

    public static ProdutoServico toProdutoServico(ProdutoServicoDto produtoServicoDto) {
        ProdutoServico produtoServico = new ProdutoServico();
        return toProdutoServico(produtoServicoDto, produtoServico);
    }

    public static ProdutoServico toProdutoServico(ProdutoServicoDto produtoServicoDto, ProdutoServico produtoServico) {
        produtoServico.setDescricao(produtoServicoDto.getDescricao());
        produtoServico.setTipo(produtoServicoDto.getTipo());
        produtoServico.setPreco(produtoServicoDto.getPreco());
        return produtoServico;
    }

}
