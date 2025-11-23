package com.projeto.desafio_java.entidades;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.MappedSuperclassType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(BaseEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class BaseEntity_ {

	
	/**
	 * @see com.projeto.desafio_java.entidades.BaseEntity#ativo
	 **/
	public static volatile SingularAttribute<BaseEntity, Boolean> ativo;
	
	/**
	 * @see com.projeto.desafio_java.entidades.BaseEntity#id
	 **/
	public static volatile SingularAttribute<BaseEntity, UUID> id;
	
	/**
	 * @see com.projeto.desafio_java.entidades.BaseEntity
	 **/
	public static volatile MappedSuperclassType<BaseEntity> class_;

	public static final String ATIVO = "ativo";
	public static final String ID = "id";

}

