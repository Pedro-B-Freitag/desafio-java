package com.projeto.desafio_java.entidades;

import com.projeto.desafio_java.enums.StatusPedidoEnum;
import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Pedido.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Pedido_ extends com.projeto.desafio_java.entidades.BaseEntity_ {

	
	/**
	 * @see com.projeto.desafio_java.entidades.Pedido#percentualDesconto
	 **/
	public static volatile SingularAttribute<Pedido, BigDecimal> percentualDesconto;
	
	/**
	 * @see com.projeto.desafio_java.entidades.Pedido#itens
	 **/
	public static volatile ListAttribute<Pedido, ItemPedido> itens;
	
	/**
	 * @see com.projeto.desafio_java.entidades.Pedido#descontoEfetivo
	 **/
	public static volatile SingularAttribute<Pedido, BigDecimal> descontoEfetivo;
	
	/**
	 * @see com.projeto.desafio_java.entidades.Pedido
	 **/
	public static volatile EntityType<Pedido> class_;
	
	/**
	 * @see com.projeto.desafio_java.entidades.Pedido#valorFinal
	 **/
	public static volatile SingularAttribute<Pedido, BigDecimal> valorFinal;
	
	/**
	 * @see com.projeto.desafio_java.entidades.Pedido#valorTotalItens
	 **/
	public static volatile SingularAttribute<Pedido, BigDecimal> valorTotalItens;
	
	/**
	 * @see com.projeto.desafio_java.entidades.Pedido#status
	 **/
	public static volatile SingularAttribute<Pedido, StatusPedidoEnum> status;

	public static final String PERCENTUAL_DESCONTO = "percentualDesconto";
	public static final String ITENS = "itens";
	public static final String DESCONTO_EFETIVO = "descontoEfetivo";
	public static final String VALOR_FINAL = "valorFinal";
	public static final String VALOR_TOTAL_ITENS = "valorTotalItens";
	public static final String STATUS = "status";

}

