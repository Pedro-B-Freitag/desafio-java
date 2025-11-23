package com.projeto.desafio_java.controllers;

import com.projeto.desafio_java.dtos.ItemPedidoDto;
import com.projeto.desafio_java.entidades.ItemPedido;
import com.projeto.desafio_java.servicos.ItemPedidoService;
import com.projeto.desafio_java.servicos.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/itensPedidos")
public class ItemPedidoController extends BaseController<ItemPedido, UUID, ItemPedidoDto> {

    private final PedidoService pedidoService;

    protected ItemPedidoController(ItemPedidoService service, PedidoService pedidoService) {
        super(service);
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ItemPedido criar(@RequestBody ItemPedidoDto dto) {
        ItemPedido itemPedido = servico.criar(dto);
        pedidoService.atualizarDescontos(itemPedido.getPedido());
        return itemPedido;
    }

    @PutMapping("/{id}")
    public ItemPedido atualizar(@PathVariable UUID id, @RequestBody ItemPedidoDto dto) {
        ItemPedido itemPedido = servico.salvar(dto, id);
        pedidoService.atualizarDescontos(itemPedido.getPedido());
        return itemPedido;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        ItemPedido itemPedido = servico.deletar(id);
        pedidoService.atualizarDescontos(itemPedido.getPedido());
    }
}
