package com.projeto.desafio_java.entidades;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProdutoServico is a Querydsl query type for ProdutoServico
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProdutoServico extends EntityPathBase<ProdutoServico> {

    private static final long serialVersionUID = 511872655L;

    public static final QProdutoServico produtoServico = new QProdutoServico("produtoServico");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final BooleanPath ativo = _super.ativo;

    public final StringPath descricao = createString("descricao");

    //inherited
    public final ComparablePath<java.util.UUID> id = _super.id;

    public final NumberPath<java.math.BigDecimal> preco = createNumber("preco", java.math.BigDecimal.class);

    public final EnumPath<com.projeto.desafio_java.enums.TipoProdutoServicoEnum> tipo = createEnum("tipo", com.projeto.desafio_java.enums.TipoProdutoServicoEnum.class);

    public QProdutoServico(String variable) {
        super(ProdutoServico.class, forVariable(variable));
    }

    public QProdutoServico(Path<? extends ProdutoServico> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProdutoServico(PathMetadata metadata) {
        super(ProdutoServico.class, metadata);
    }

}

