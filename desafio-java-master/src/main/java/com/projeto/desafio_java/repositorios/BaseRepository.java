package com.projeto.desafio_java.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T,ID> {
    public Page<T> buscarComFiltros(Pageable pageable, Map<String, Object> filtros);

}
