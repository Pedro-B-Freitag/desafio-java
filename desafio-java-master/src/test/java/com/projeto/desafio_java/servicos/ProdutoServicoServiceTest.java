package com.projeto.desafio_java.servicos;

import com.projeto.desafio_java.dtos.ProdutoServicoDto;
import com.projeto.desafio_java.entidades.ProdutoServico;
import com.projeto.desafio_java.enums.TipoProdutoServicoEnum;
import com.projeto.desafio_java.exceptions.BadRequestException;
import com.projeto.desafio_java.repositorios.ItemPedidoRepository;
import com.projeto.desafio_java.repositorios.ProdutoServicoRepository;
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
class ProdutoServicoServiceTest {

    @Mock private ProdutoServicoRepository produtoServicoRepository;
    @Mock private ItemPedidoRepository itemPedidoRepository;
    private ProdutoServicoService produtoServicoService;
    private UUID produtoId;

    @BeforeEach
    void setUp() {
        produtoServicoService = new ProdutoServicoService(produtoServicoRepository, itemPedidoRepository);
        produtoId = UUID.randomUUID();
    }

    @Test
    void testCriar() {
        ProdutoServicoDto dto = new ProdutoServicoDto("Produto Teste", TipoProdutoServicoEnum.PRODUTO, new BigDecimal("100.00"));
        ProdutoServico produto = new ProdutoServico();
        produto.setDescricao("Produto Teste");
        produto.setPreco(new BigDecimal("100.00"));
        produto.setTipo(TipoProdutoServicoEnum.PRODUTO);

        when(produtoServicoRepository.save(any(ProdutoServico.class))).thenReturn(produto);

        try (MockedStatic<DtoUtils> dtoUtils = mockStatic(DtoUtils.class)) {
            dtoUtils.when(() -> DtoUtils.toProdutoServico(dto)).thenReturn(produto);
            ProdutoServico resultado = produtoServicoService.criar(dto);
            assertNotNull(resultado);
        }
    }

    @Test
    void testValidarInsert1() {
        ProdutoServico produto = new ProdutoServico();
        produto.setDescricao("");
        assertThrows(BadRequestException.class, () -> produtoServicoService.validarInsert(produto));
    }

    @Test
    void testValidarInsert2() {
        ProdutoServico produto = new ProdutoServico();
        produto.setDescricao("Produto Teste");
        produto.setPreco(new BigDecimal("-10"));
        assertThrows(BadRequestException.class, () -> produtoServicoService.validarInsert(produto));
    }

    @Test
    void testValidarInsert3() {
        ProdutoServico produto = new ProdutoServico();
        produto.setDescricao("Produto Teste");
        produto.setPreco(new BigDecimal("100"));
        produto.setTipo(null);
        assertThrows(BadRequestException.class, () -> produtoServicoService.validarInsert(produto));
    }

    @Test
    void testBuscarPorId() {
        ProdutoServico produto = new ProdutoServico();
        produto.setId(produtoId);

        when(produtoServicoRepository.findById(produtoId)).thenReturn(Optional.of(produto));

        ProdutoServico resultado = produtoServicoService.buscarPorId(produtoId);

        assertNotNull(resultado);
    }

}