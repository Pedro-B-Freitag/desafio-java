package com.projeto.desafio_java.servicos;

import com.projeto.desafio_java.utils.DtoUtils;
import com.projeto.desafio_java.dtos.ItemPedidoDto;
import com.projeto.desafio_java.entidades.ItemPedido;
import com.projeto.desafio_java.entidades.Pedido;
import com.projeto.desafio_java.entidades.ProdutoServico;
import com.projeto.desafio_java.enums.StatusPedidoEnum;
import com.projeto.desafio_java.exceptions.BadRequestException;
import com.projeto.desafio_java.repositorios.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemPedidoService extends BaseService<ItemPedido, UUID, ItemPedidoDto> {

    public final ItemPedidoRepository itemPedidoRepository;
    public final PedidoService pedidoService;
    public final ProdutoServicoService produtoServicoService;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, PedidoService pedidoService, ProdutoServicoService produtoServicoService) {
        super(itemPedidoRepository);
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoService = pedidoService;
        this.produtoServicoService = produtoServicoService;
    }

    public List<ItemPedido> buscarPorPedido(UUID idPedido){
        return itemPedidoRepository.buscarTodosPeloPedido(idPedido);
    }

    @Override
    void validarInsert(ItemPedido entidade) {
        if(entidade.getPedido() == null){
            throw new BadRequestException("Informe o pedido!");
        }
        if(entidade.getProduto() == null){
            throw new BadRequestException("Informe o produto!");
        }
        if(!entidade.getProduto().isAtivo()){
            throw new BadRequestException("O produto deve estar ativo!");
        }
        if(entidade.getQuantidade() <= 0){
            throw new BadRequestException("Não é possível inserir itens com quantidade igual ou menor a zero!");
        }
        if(entidade.getPedido().getStatus() == StatusPedidoEnum.FECHADO){
            throw new BadRequestException("Não é possível inserir itens em pedidos fechados!");
        }
    }

    @Override
    void validarDelete(ItemPedido entidade) {
        if(entidade.getPedido().getStatus() == StatusPedidoEnum.FECHADO){
            throw new BadRequestException("Não é possível remover itens em pedidos fechados!");
        }
    }

    @Override
    void validarAlter(ItemPedido entidade) {
        if(entidade.getPedido() == null){
            throw new BadRequestException("Informe o pedido!");
        }
        if(entidade.getProduto() == null){
            throw new BadRequestException("Informe o produto!");
        }
        if(!entidade.getProduto().isAtivo()){
            throw new BadRequestException("O produto deve estar ativo!");
        }
        if(entidade.getQuantidade() <= 0){
            throw new BadRequestException("Não é possível inserir itens com quantidade igual ou menor a zero!");
        }
        if(entidade.getPedido().getStatus() == StatusPedidoEnum.FECHADO){
            throw new BadRequestException("Não é possível inserir itens em pedidos fechados!");
        }
    }

    @Override
    ItemPedido toEntidade(ItemPedidoDto itemPedidoDto) {
        Pedido pedido = pedidoService.buscarPorId(itemPedidoDto.getPedidoId());
        ProdutoServico produto = produtoServicoService.buscarPorId(itemPedidoDto.getProdutoId());
        return DtoUtils.toItemPedido(itemPedidoDto,pedido,produto);
    }

    @Override
    ItemPedido toEntidade(ItemPedidoDto itemPedidoDto, ItemPedido entidade) {
        Pedido pedido = pedidoService.buscarPorId(itemPedidoDto.getPedidoId());
        ProdutoServico produto = produtoServicoService.buscarPorId(itemPedidoDto.getProdutoId());
        return DtoUtils.toItemPedido(itemPedidoDto, entidade, pedido, produto);
    }
}
