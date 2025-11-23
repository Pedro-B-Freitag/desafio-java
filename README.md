# Documentação do Projeto Desafio Java

## Pré-requisitos

-   Java 17 ou superior
-   Maven 3.6 ou superior
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
<p>A compilação deve gerar as classes do queryDSL, se isso não acontecer, verifique se o pom.xml foi clonado corretamente e execute o comando novamente</p>
### Executar aplicação:

``` bash
mvn spring-boot:run
```

### 2. Acessar a Aplicação

-   API: http://localhost:8080
-   Swagger UI: http://localhost:8080/swagger-ui.html
-   Documentação OpenAPI: http://localhost:8080/api-docs

## Execução dos Testes

### Testes Unitários

<p>Os testes dão cobertura aos services da aplicação, pois são neles em que tratamos as requisições e os dados que serão inseridos/ buscados do banco de dados e os resultados mostrados nos endpoints.</p>

Para executar todos de uma vez:
``` bash
mvn test
```
Para executar unitarios:
``` bash
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

### Listar Pedidos

``` http
GET /pedidos
```

### Buscar Pedido por ID

``` http
GET /pedidos/{id}
```

### Atualizar Pedido

``` http
PUT /pedidos/{id}
```

### Deletar Pedido

``` http
DELETE /pedidos/{id}
```

### Aplicar Desconto

``` json
{
  "percentual": 15.0
}
```

### Fechar pedido

``` http
POST /pedidos/{id}/fechar
````

# Itens do Pedido

### Listar todos os Itens de todos os Pedidos

``` http
GET /itensPedidos
```

### Buscar Item do Pedido por ID

``` http
GET /itensPedidos/{id}
```

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

A aplicação estará disponível em: http://localhost:8080/swagger-ui/index.html ou no postman acessando os endpoints da aplicação conforme mencionado acima
