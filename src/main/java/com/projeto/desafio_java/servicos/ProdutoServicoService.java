package com.projeto.desafio_java.servicos;

import com.projeto.desafio_java.utils.DtoUtils;
import com.projeto.desafio_java.dtos.ProdutoServicoDto;
import com.projeto.desafio_java.entidades.ProdutoServico;
import com.projeto.desafio_java.exceptions.BadRequestException;
import com.projeto.desafio_java.repositorios.ItemPedidoRepository;
import com.projeto.desafio_java.repositorios.ProdutoServicoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class ProdutoServicoService extends BaseService<ProdutoServico, UUID, ProdutoServicoDto> {

    private final ItemPedidoRepository itemPedidoRepository;

    public ProdutoServicoService(ProdutoServicoRepository produtoServicoRepository, ItemPedidoRepository itemPedidoRepository) {
        super(produtoServicoRepository);
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    void validarInsert(ProdutoServico entidade) {
        if (entidade.getDescricao() == null || entidade.getDescricao().isBlank()) {
            throw new BadRequestException("Descrição é obrigatória.");
        }
        if (entidade.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Preço deve ser maior ou igual a zero.");
        }
        if (entidade.getTipo() == null) {
            throw new BadRequestException("Tipo deve ser informado.");
        }
    }

    @Override
    void validarDelete(ProdutoServico entidade) {
        if(itemPedidoRepository.possuiPedido(entidade.getId())){
            throw new BadRequestException("Não é possível excluir produtos vinculados a pedidos!");
        }
    }

    @Override
    void validarAlter(ProdutoServico entidade) {

    }

    @Override
    ProdutoServico toEntidade(ProdutoServicoDto produtoServicoDto) {
        return DtoUtils.toProdutoServico(produtoServicoDto);
    }

    @Override
    ProdutoServico toEntidade(ProdutoServicoDto produtoServicoDto, ProdutoServico entidade) {
        return DtoUtils.toProdutoServico(produtoServicoDto, entidade);
    }
}
