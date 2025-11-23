package com.projeto.desafio_java.entidades;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPedido is a Querydsl query type for Pedido
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPedido extends EntityPathBase<Pedido> {

    private static final long serialVersionUID = 1557809486L;

    public static final QPedido pedido = new QPedido("pedido");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final BooleanPath ativo = _super.ativo;

    public final NumberPath<java.math.BigDecimal> descontoEfetivo = createNumber("descontoEfetivo", java.math.BigDecimal.class);

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final ListPath<ItemPedido, QItemPedido> itens = this.<ItemPedido, QItemPedido>createList("itens", ItemPedido.class, QItemPedido.class, PathInits.DIRECT2);

    public final NumberPath<java.math.BigDecimal> percentualDesconto = createNumber("percentualDesconto", java.math.BigDecimal.class);

    public final EnumPath<com.projeto.desafio_java.enums.StatusPedidoEnum> status = createEnum("status", com.projeto.desafio_java.enums.StatusPedidoEnum.class);

    public final NumberPath<java.math.BigDecimal> valorFinal = createNumber("valorFinal", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> valorTotalItens = createNumber("valorTotalItens", java.math.BigDecimal.class);

    public QPedido(String variable) {
        super(Pedido.class, forVariable(variable));
    }

    public QPedido(Path<? extends Pedido> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPedido(PathMetadata metadata) {
        super(Pedido.class, metadata);
    }

}

