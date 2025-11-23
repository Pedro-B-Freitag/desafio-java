# Documentação do Projeto Desafio Java

## Pré-requisitos

-   Java 17 ou superior\
-   Maven 3.6 ou superior\
-   PostgreSQL 14 ou superior

## Configuração da Aplicação

### Arquivo `application.properties`:

``` properties
spring.datasource.url=jdbc:postgresql://localhost(porta do postgres padrão: 5432)/desafio-java
spring.datasource.username=(Usuário do banco de dados)
spring.datasource.password=(SEnha do banco de dados)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
```

### Arquivo `application-test.properties`:

``` properties
spring.datasource.url=jdbc:postgresql://localhost(porta do postgres padrão: 5432)/desafio-java-test
spring.datasource.username=(Usuário do banco de dados)
spring.datasource.password=(SEnha do banco de dados)
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.profiles.active=test
```

## Execução da Aplicação

### 1. Compilar

``` bash
mvn clean compile
```

### Executar aplicação:

``` bash
mvn spring-boot:run
```

### 2. Acessar a Aplicação

-   API: http://localhost:8080\
-   Swagger UI: http://localhost:8080/swagger-ui.html\
-   Documentação OpenAPI: http://localhost:8080/api-docs

## Execução dos Testes

### Testes Unitários

``` bash
mvn test
mvn test -Dtest=BaseServiceTest
mvn test -Dtest=PedidoServiceTest
mvn test -Dtest=ItemPedidoServiceTest
mvn test -Dtest=ProdutoServicoServiceTest
```

## Endpoints da API

# Produtos e Serviços

### Listar Produtos/Serviços

    GET /produtosServicos?page=0&size=10&sort=nome,asc

### Exemplo de filtros:

``` json
{
  "descricao": "notebook",
  "preco": 1000,
  "tipo": "PRODUTO"
}
```

### Buscar por ID

    GET /produtosServicos/{id}

### Criar Produto/Serviço

``` json
{
  "descricao": "Notebook Dell",
  "tipo": "PRODUTO",
  "preco": 2500.00
}
```

### Atualizar Produto/Serviço

``` json
{
  "descricao": "Notebook Dell alterado",
  "tipo": "PRODUTO",
  "preco": 2300.00
}
```

### Deletar Produto/Serviço

    DELETE /produtosServicos/{id}

# Pedidos

### Criar Pedido

``` json
{
  "status": "ABERTO"
}
```

### Aplicar Desconto

``` json
{
  "percentual": 15.0
}
```

# Itens do Pedido

### Adicionar Item ao Pedido

``` json
{
  "pedidoId": "uuid-do-pedido",
  "produtoId": "uuid-do-produto",
  "quantidade": 2
}
```

### Atualizar Item do Pedido

``` json
{
  "pedidoId": "uuid-do-pedido",
  "produtoId": "uuid-do-produto",
  "quantidade": 3
}
```

A aplicação estará disponível em
http://localhost:8080/swagger-ui/index.html
