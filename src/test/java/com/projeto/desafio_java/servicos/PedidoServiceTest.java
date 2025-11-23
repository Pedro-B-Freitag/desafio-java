package com.projeto.desafio_java.servicos;

import com.projeto.desafio_java.dtos.PedidoDto;
import com.projeto.desafio_java.entidades.Pedido;
import com.projeto.desafio_java.enums.StatusPedidoEnum;
import com.projeto.desafio_java.exceptions.BadRequestException;
import com.projeto.desafio_java.repositorios.ItemPedidoRepository;
import com.projeto.desafio_java.repositorios.PedidoRepository;
import com.projeto.desafio_java.utils.DtoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock private PedidoRepository pedidoRepository;
    @Mock private ItemPedidoRepository itemPedidoRepository;

    private PedidoService pedidoService;
    private UUID pedidoId;

    @BeforeEach
    void setUp() {
        pedidoService = new PedidoService(pedidoRepository, itemPedidoRepository);
        pedidoId = UUID.randomUUID();
    }

    @Test
    void testCriar() {
        PedidoDto dto = new PedidoDto(StatusPedidoEnum.ABERTO);
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedidoEnum.ABERTO);

        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        try (MockedStatic<DtoUtils> dtoUtils = mockStatic(DtoUtils.class)) {
            dtoUtils.when(() -> DtoUtils.toPedido(dto)).thenReturn(pedido);
            Pedido resultado = pedidoService.criar(dto);
            assertNotNull(resultado);
        }
    }

    @Test
    void testFecharPedido1() {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatus(StatusPedidoEnum.ABERTO);

        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido resultado = pedidoService.fecharPedido(pedidoId);

        assertEquals(StatusPedidoEnum.FECHADO, resultado.getStatus());
    }

    @Test
    void testFecharPedido2() {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setStatus(StatusPedidoEnum.FECHADO);

        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        assertThrows(BadRequestException.class, () -> pedidoService.fecharPedido(pedidoId));
    }

    @Test
    void testBuscarPorId() {
        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);

        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.buscarPorId(pedidoId);

        assertNotNull(resultado);
    }
}