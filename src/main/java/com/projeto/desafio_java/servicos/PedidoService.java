package com.projeto.desafio_java.servicos;

import com.projeto.desafio_java.dtos.ItemPedidoDto;
import com.projeto.desafio_java.entidades.ItemPedido;
import com.projeto.desafio_java.utils.DtoUtils;
import com.projeto.desafio_java.dtos.PedidoDto;
import com.projeto.desafio_java.enums.StatusPedidoEnum;
import com.projeto.desafio_java.entidades.Pedido;
import com.projeto.desafio_java.exceptions.BadRequestException;
import com.projeto.desafio_java.repositorios.ItemPedidoRepository;
import com.projeto.desafio_java.repositorios.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService extends BaseService<Pedido, UUID, PedidoDto> {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository) {
        super(pedidoRepository);
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public Pedido aplicarDescontos(UUID id, BigDecimal porcentagem) {
        Pedido pedido = buscarPorId(id);
        if(pedido.getStatus() == StatusPedidoEnum.FECHADO){
            throw new BadRequestException("Não é possível aplicar descontos a pedidos fechados!");
        }
        pedido.setPercentualDesconto(porcentagem);
        return calcularTotais(pedido);
    }

    private void calcularDescontos(Pedido pedido) {
        BigDecimal valorBrutoProdutos = itemPedidoRepository.calcularValorTotalProdutosNoPedido(pedido.getId());
        pedido.setDescontoEfetivo(BigDecimal.ZERO);

        if(valorBrutoProdutos.compareTo(BigDecimal.ZERO) > 0 && pedido.getPercentualDesconto() != null){
            BigDecimal valorDesconto = valorBrutoProdutos.multiply(pedido.getPercentualDesconto()).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
            pedido.setDescontoEfetivo(valorDesconto);
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido calcularTotais(Pedido pedido){
        calcularDescontos(pedido);
        pedido.setValorTotalItens(itemPedidoRepository.calcularValorTotalItens(pedido.getId()));
        pedido.setValorFinal(pedido.getValorTotalItens().subtract(pedido.getDescontoEfetivo()));
        return pedidoRepository.save(pedido);
    }

    public Pedido fecharPedido(UUID id){
        Pedido pedido = buscarPorId(id);
        if(pedido.getStatus() == StatusPedidoEnum.FECHADO){
            throw new BadRequestException("Pedido já está fechado!");
        }
        pedido.setStatus(StatusPedidoEnum.FECHADO);
        return pedidoRepository.save(pedido);
    }

    public List<ItemPedido> obterItensPedido(UUID id){
        Pedido pedido = buscarPorId(id);
        return itemPedidoRepository.buscarTodosPeloPedido(pedido.getId());
    }

    @Override
    public Pedido deletar(UUID id) {
        Pedido entidade = buscarPorId(id);
        validarDelete(entidade);
        entidade.setAtivo(false);
        for (ItemPedido itemPedido : entidade.getItens()) {
            itemPedido.setAtivo(false);
            itemPedidoRepository.save(itemPedido);
        }
        return repository.save(entidade);
    }

    @Override
    void validarInsert(Pedido entidade) {
    }

    @Override
    void validarDelete(Pedido entidade) {
    }

    @Override
    void validarAlter(Pedido entidade) {
    }

    @Override
    Pedido toEntidade(PedidoDto pedidoDto) {
        return DtoUtils.toPedido(pedidoDto);
    }

    @Override
    Pedido toEntidade(PedidoDto pedidoDto, Pedido entidade) {
        return DtoUtils.toPedido(pedidoDto, entidade);
    }

}
