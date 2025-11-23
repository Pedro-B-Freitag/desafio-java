package com.projeto.desafio_java.servicos;

import com.projeto.desafio_java.entidades.BaseEntity;
import com.projeto.desafio_java.exceptions.NotFoundException;
import com.projeto.desafio_java.repositorios.BaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BaseServiceTest {

    @Mock
    private BaseRepository<TestEntity, UUID> repository;

    private TestBaseService service;

    @BeforeEach
    void setUp() {
        service = new TestBaseService(repository);
    }

    @Test
    void testCriar() {
        String dto = "Test DTO";
        TestEntity entidade = new TestEntity(UUID.randomUUID(), "Test");
        when(repository.save(any(TestEntity.class))).thenReturn(entidade);

        TestEntity resultado = service.criar(dto);

        assertNotNull(resultado);
        verify(repository).save(any(TestEntity.class));
    }

    @Test
    void testSalvar() {
        UUID id = UUID.randomUUID();
        String dto = "Updated DTO";
        TestEntity entidadeExistente = new TestEntity(id, "Original");
        TestEntity entidadeAtualizada = new TestEntity(id, "Updated");

        when(repository.findById(id)).thenReturn(Optional.of(entidadeExistente));
        when(repository.save(any(TestEntity.class))).thenReturn(entidadeAtualizada);

        TestEntity resultado = service.salvar(dto, id);

        assertNotNull(resultado);
        verify(repository).findById(id);
        verify(repository).save(any(TestEntity.class));
    }

    @Test
    void testBuscarPorId() {
        UUID id = UUID.randomUUID();
        TestEntity entidade = new TestEntity(id, "Test");
        when(repository.findById(id)).thenReturn(Optional.of(entidade));

        TestEntity resultado = service.buscarPorId(id);

        assertNotNull(resultado);
    }

    @Test
    void testBuscarPorId_NaoEncontrado() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.buscarPorId(id));
    }

    @Test
    void testListar() {
        Pageable pageable = PageRequest.of(0, 10);
        Map<String, Object> filtros = Map.of("nome", "teste");
        List<TestEntity> entidades = Arrays.asList(
                new TestEntity(UUID.randomUUID(), "Test1"),
                new TestEntity(UUID.randomUUID(), "Test2")
        );
        Page<TestEntity> page = new PageImpl<>(entidades);

        when(repository.buscarComFiltros(any(Pageable.class), any(Map.class))).thenReturn(page);

        List<TestEntity> resultado = service.listar(pageable, filtros);

        assertEquals(2, resultado.size());
    }

    @Test
    void testDeletar() {
        UUID id = UUID.randomUUID();
        TestEntity entidade = new TestEntity(id, "Test");
        when(repository.findById(id)).thenReturn(Optional.of(entidade));
        when(repository.save(any(TestEntity.class))).thenReturn(entidade);

        TestEntity resultado = service.deletar(id);

        assertFalse(resultado.isAtivo());
        verify(repository).save(entidade);
    }

    @Test
    void testExistePorId() {
        UUID id = UUID.randomUUID();
        when(repository.existsById(id)).thenReturn(true);

        boolean resultado = service.existePorId(id);

        assertTrue(resultado);
    }

    private static class TestBaseService extends BaseService<TestEntity, UUID, String> {
        public TestBaseService(BaseRepository<TestEntity, UUID> repository) {
            super(repository);
        }

        @Override void validarInsert(TestEntity entidade) {}
        @Override void validarDelete(TestEntity entidade) {}
        @Override void validarAlter(TestEntity entidade) {}
        @Override TestEntity toEntidade(String dto) { return new TestEntity(UUID.randomUUID(), dto); }
        @Override TestEntity toEntidade(String dto, TestEntity entidade) { entidade.setNome(dto); return entidade; }
    }

    private static class TestEntity extends BaseEntity {
        private String nome;
        public TestEntity(UUID id, String nome) { setId(id); this.nome = nome; setAtivo(true); }
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
    }
}