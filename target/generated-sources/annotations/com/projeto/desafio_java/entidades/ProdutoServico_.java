package com.projeto.desafio_java.entidades;

import com.projeto.desafio_java.enums.TipoProdutoServicoEnum;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(ProdutoServico.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ProdutoServico_ extends com.projeto.desafio_java.entidades.BaseEntity_ {

	
	/**
	 * @see com.projeto.desafio_java.entidades.ProdutoServico#preco
	 **/
	public static volatile SingularAttribute<ProdutoServico, BigDecimal> preco;
	
	/**
	 * @see com.projeto.desafio_java.entidades.ProdutoServico#tipo
	 **/
	public static volatile SingularAttribute<ProdutoServico, TipoProdutoServicoEnum> tipo;
	
	/**
	 * @see com.projeto.desafio_java.entidades.ProdutoServico
	 **/
	public static volatile EntityType<ProdutoServico> class_;
	
	/**
	 * @see com.projeto.desafio_java.entidades.ProdutoServico#descricao
	 **/
	public static volatile SingularAttribute<ProdutoServico, String> descricao;

	public static final String PRECO = "preco";
	public static final String TIPO = "tipo";
	public static final String DESCRICAO = "descricao";

}

