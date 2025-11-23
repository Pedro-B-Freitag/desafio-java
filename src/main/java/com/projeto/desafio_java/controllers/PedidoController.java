package com.projeto.desafio_java.controllers;

import com.projeto.desafio_java.dtos.PedidoDto;
import com.projeto.desafio_java.entidades.ItemPedido;
import com.projeto.desafio_java.entidades.Pedido;
import com.projeto.desafio_java.servicos.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController extends BaseController<Pedido, UUID, PedidoDto> {

    private final PedidoService pedidoService;

    protected PedidoController(PedidoService service) {
        super(service);
        this.pedidoService = service;
    }

    @PutMapping("/{id}/desconto")
    public ResponseEntity<Pedido> aplicarDesconto(@PathVariable UUID id, @RequestParam BigDecimal percentual) {
        return ResponseEntity.ok(pedidoService.aplicarDescontos(id, percentual));
    }

    @PostMapping("/{id}/fechar")
    public ResponseEntity<Pedido> fechar(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoService.fecharPedido(id));
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemPedido>> listarItensPedido(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoService.obterItensPedido(id));
    }
}
