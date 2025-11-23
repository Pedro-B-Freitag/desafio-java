package com.projeto.desafio_java.servicos;

import com.projeto.desafio_java.entidades.BaseEntity;
import com.projeto.desafio_java.exceptions.NotFoundException;
import com.projeto.desafio_java.repositorios.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public abstract class BaseService<T extends BaseEntity, ID, DTO> {

    protected final BaseRepository<T, ID> repository;

    public T criar(DTO dto) {
        T entidade = toEntidade(dto);
        validarInsert(entidade);
        return repository.save(entidade);
    }

    public T salvar(DTO dto, ID id) {
        T entidade = buscarPorId(id);
        entidade = toEntidade(dto, entidade);
        validarAlter(entidade);
        return repository.save(entidade);
    }

    public T buscarPorId(ID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Não foi possível encontrar objeto com o id: " + id));
    }

    public List<T> listar(Pageable pageable, Map<String, Object> filtros) {
        if (filtros != null) {
            filtros.keySet().removeIf(k ->
                    k.equals("page") || k.equals("size") || k.equals("sort")
            );
        }
        return repository.buscarComFiltros(pageable, filtros).getContent();
    }

    public T deletar(ID id) {
        T entidade = buscarPorId(id);
        validarDelete(entidade);
        entidade.setAtivo(false);
        return repository.save(entidade);
    }

    public boolean existePorId(ID id) {
        return repository.existsById(id);
    }

    abstract void validarInsert(T entidade);
    abstract void validarDelete(T entidade);
    abstract void validarAlter(T entidade);
    abstract T toEntidade(DTO dto);
    abstract T toEntidade(DTO dto, T entidade);


}