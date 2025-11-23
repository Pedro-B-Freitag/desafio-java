package com.projeto.desafio_java.entidades;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ItemPedido.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ItemPedido_ extends com.projeto.desafio_java.entidades.BaseEntity_ {

	
	/**
	 * @see com.projeto.desafio_java.entidades.ItemPedido#produto
	 **/
	public static volatile SingularAttribute<ItemPedido, ProdutoServico> produto;
	
	/**
	 * @see com.projeto.desafio_java.entidades.ItemPedido#pedido
	 **/
	public static volatile SingularAttribute<ItemPedido, Pedido> pedido;
	
	/**
	 * @see com.projeto.desafio_java.entidades.ItemPedido
	 **/
	public static volatile EntityType<ItemPedido> class_;
	
	/**
	 * @see com.projeto.desafio_java.entidades.ItemPedido#quantidade
	 **/
	public static volatile SingularAttribute<ItemPedido, Integer> quantidade;

	public static final String PRODUTO = "produto";
	public static final String PEDIDO = "pedido";
	public static final String QUANTIDADE = "quantidade";

}

