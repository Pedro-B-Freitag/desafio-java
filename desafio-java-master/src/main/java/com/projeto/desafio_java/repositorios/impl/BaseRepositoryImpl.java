package com.projeto.desafio_java.repositorios.impl;

import com.projeto.desafio_java.repositorios.BaseRepository;
import com.projeto.desafio_java.utils.FiltroUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Map;

public abstract class BaseRepositoryImpl<T,ID> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID> {

    protected final EntityManager em;
    protected JPAQueryFactory jpaQueryFactory;
    protected final Class<T> entityClass;
    protected final PathBuilder<T> entityPath;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
        this.entityClass = domainClass;
        this.entityPath = new PathBuilder<>(entityClass, domainClass.getSimpleName());
    }

    @Override
    public Page<T> buscarComFiltros(Pageable pageable, Map<String, Object> filtros) {

        BooleanBuilder filtroBuilder = FiltroUtils.montarFiltros(filtros, entityPath);

        var resultados = jpaQueryFactory
                .select(entityPath)
                .from(entityPath)
                .where(filtroBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(entityPath.count())
                .from(entityPath)
                .where(filtroBuilder)
                .fetchOne();

        return new PageImpl<>(resultados, pageable, total != null ? total : 0);
    }


}
