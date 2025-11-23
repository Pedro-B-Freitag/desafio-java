package com.projeto.desafio_java.servicos;

import com.projeto.desafio_java.dtos.ItemPedidoDto;
import com.projeto.desafio_java.entidades.ItemPedido;
import com.projeto.desafio_java.entidades.Pedido;
import com.projeto.desafio_java.entidades.ProdutoServico;
import com.projeto.desafio_java.enums.StatusPedidoEnum;
import com.projeto.desafio_java.exceptions.BadRequestException;
import com.projeto.desafio_java.repositorios.ItemPedidoRepository;
import com.projeto.desafio_java.utils.DtoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemPedidoServiceTest {

    @Mock private ItemPedidoRepository itemPedidoRepository;
    @Mock private PedidoService pedidoService;
    @Mock private ProdutoServicoService produtoServicoService;

    private ItemPedidoService itemPedidoService;
    private UUID itemId, pedidoId, produtoId;

    @BeforeEach
    void setUp() {
        itemPedidoService = new ItemPedidoService(itemPedidoRepository, pedidoService, produtoServicoService);
        itemId = UUID.randomUUID();
        pedidoId = UUID.randomUUID();
        produtoId = UUID.randomUUID();
    }

    @Test
    void testCriar() {
        ItemPedidoDto dto = new ItemPedidoDto(pedidoId, produtoId, 2);
        Pedido pedido = criarPedido(StatusPedidoEnum.ABERTO);
        ProdutoServico produto = criarProdutoAtivo();
        ItemPedido itemPedido = criarItemPedido(pedido, produto, 2);

        when(pedidoService.buscarPorId(pedidoId)).thenReturn(pedido);
        when(produtoServicoService.buscarPorId(produtoId)).thenReturn(produto);
        when(itemPedidoRepository.save(any(ItemPedido.class))).thenReturn(itemPedido);

        try (MockedStatic<DtoUtils> dtoUtils = mockStatic(DtoUtils.class)) {
            dtoUtils.when(() -> DtoUtils.toItemPedido(dto, pedido, produto)).thenReturn(itemPedido);
            ItemPedido resultado = itemPedidoService.criar(dto);
            assertNotNull(resultado);
        }
    }

    @Test
    void testCriar2() {
        ItemPedidoDto dto = new ItemPedidoDto(null, produtoId, 2);
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(null);

        try (MockedStatic<DtoUtils> dtoUtils = mockStatic(DtoUtils.class)) {
            dtoUtils.when(() -> DtoUtils.toItemPedido(any(), any(), any())).thenReturn(itemPedido);
            assertThrows(BadRequestException.class, () -> itemPedidoService.criar(dto));
        }
    }

    @Test
    void testCriar3() {
        ItemPedidoDto dto = new ItemPedidoDto(pedidoId, produtoId, 2);
        Pedido pedido = criarPedido(StatusPedidoEnum.ABERTO);
        ProdutoServico produto = criarProdutoInativo();
        ItemPedido itemPedido = criarItemPedido(pedido, produto, 2);

        when(pedidoService.buscarPorId(pedidoId)).thenReturn(pedido);
        when(produtoServicoService.buscarPorId(produtoId)).thenReturn(produto);

        try (MockedStatic<DtoUtils> dtoUtils = mockStatic(DtoUtils.class)) {
            dtoUtils.when(() -> DtoUtils.toItemPedido(dto, pedido, produto)).thenReturn(itemPedido);
            assertThrows(BadRequestException.class, () -> itemPedidoService.criar(dto));
        }
    }

    @Test
    void testCriar4() {
        ItemPedidoDto dto = new ItemPedidoDto(pedidoId, produtoId, 0);
        Pedido pedido = criarPedido(StatusPedidoEnum.ABERTO);
        ProdutoServico produto = criarProdutoAtivo();
        ItemPedido itemPedido = criarItemPedido(pedido, produto, 0);

        when(pedidoService.buscarPorId(pedidoId)).thenReturn(pedido);
        when(produtoServicoService.buscarPorId(produtoId)).thenReturn(produto);

        try (MockedStatic<DtoUtils> dtoUtils = mockStatic(DtoUtils.class)) {
            dtoUtils.when(() -> DtoUtils.toItemPedido(dto, pedido, produto)).thenReturn(itemPedido);
            assertThrows(BadRequestException.class, () -> itemPedidoService.criar(dto));
        }
    }

    @Test
    void testCriar5() {
        ItemPedidoDto dto = new ItemPedidoDto(pedidoId, produtoId, 2);
        Pedido pedido = criarPedido(StatusPedidoEnum.FECHADO);
        ProdutoServico produto = criarProdutoAtivo();
        ItemPedido itemPedido = criarItemPedido(pedido, produto, 2);

        when(pedidoService.buscarPorId(pedidoId)).thenReturn(pedido);
        when(produtoServicoService.buscarPorId(produtoId)).thenReturn(produto);

        try (MockedStatic<DtoUtils> dtoUtils = mockStatic(DtoUtils.class)) {
            dtoUtils.when(() -> DtoUtils.toItemPedido(dto, pedido, produto)).thenReturn(itemPedido);
            assertThrows(BadRequestException.class, () -> itemPedidoService.criar(dto));
        }
    }

    @Test
    void testDeletar() {
        Pedido pedido = criarPedido(StatusPedidoEnum.FECHADO);
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(itemId);
        itemPedido.setPedido(pedido);

        when(itemPedidoRepository.findById(itemId)).thenReturn(Optional.of(itemPedido));
        assertThrows(BadRequestException.class, () -> itemPedidoService.deletar(itemId));
    }

    @Test
    void testBuscarPorPedido() {
        when(itemPedidoRepository.buscarTodosPeloPedido(pedidoId)).thenReturn(java.util.List.of());
        var resultado = itemPedidoService.buscarPorPedido(pedidoId);
        assertNotNull(resultado);
    }

    private Pedido criarPedido(StatusPedidoEnum status) {
        Pedido pedido = new Pedido();
        pedido.setStatus(status);
        return pedido;
    }

    private ProdutoServico criarProdutoAtivo() {
        ProdutoServico produto = new ProdutoServico();
        produto.setAtivo(true);
        return produto;
    }

    private ProdutoServico criarProdutoInativo() {
        ProdutoServico produto = new ProdutoServico();
        produto.setAtivo(false);
        return produto;
    }

    private ItemPedido criarItemPedido(Pedido pedido, ProdutoServico produto, int quantidade) {
        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        return item;
    }
}