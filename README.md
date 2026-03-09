# FórumHub API

API REST desenvolvida com **Java e Spring Boot** para gerenciamento de tópicos de um fórum.
O sistema permite autenticação de usuários, criação e gerenciamento de tópicos, controle de estado das discussões e persistência em banco de dados PostgreSQL.

Este projeto foi desenvolvido como prática de backend aplicando conceitos como **arquitetura em camadas, autenticação JWT, validações, migrations e soft delete**.

---

# Tecnologias Utilizadas

* **Java 25**
* **Spring Boot**
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (Auth0 Java JWT)
* PostgreSQL
* Flyway (Database Migrations)
* Lombok
* Maven
* Postman

---

# Arquitetura do Projeto

O projeto segue o padrão de arquitetura em camadas:

```
Controller
   ↓
Service
   ↓
Repository
   ↓
Entity
```

Também foi utilizada uma camada de **validadores** para separar regras de negócio específicas da lógica principal.

```
controller → service → validators → entity
```

---

# Funcionalidades

* Autenticação de usuários com JWT
* Criação de tópicos
* Listagem de tópicos
* Atualização de tópicos
* Conclusão de tópicos
* Desativação de tópicos (soft delete)
* Paginação de resultados
* Validação de dados
* Controle de estado dos tópicos

---

# Modelo de Dados

## Autor

Representa o criador de um tópico.

Campos:

* id
* nome
* email
* senha

---

## Curso

Curso relacionado ao tópico.

Campos:

* id
* nome
* categoria

Categorias disponíveis:

```
FRONTEND
BACKEND
DATASCIENCE
```

---

## Tópico

Representa uma discussão dentro do fórum.

Campos:

* id
* titulo
* mensagem
* dataCriacao
* estado
* autor
* curso

Estados do tópico:

```
ATIVADO
CONCLUIDO
DESATIVADO
```

---

# Soft Delete

A exclusão de tópicos é feita utilizando **soft delete**.

Ao invés de remover o registro do banco de dados, o estado do tópico é alterado para:

```
DESATIVADO
```

Isso permite manter o histórico e evitar perda de dados.

---

# Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação.

Fluxo:

1. Usuário realiza login
2. A API retorna um token JWT
3. O token deve ser enviado no header das próximas requisições

Header obrigatório:

```
Authorization: Bearer SEU_TOKEN
```

---

# Principais Endpoints

## Login

POST

```
/login
```

Body:

```json
{
  "login": "usuario@email.com",
  "senha": "123456"
}
```

---

## Criar Tópico

POST

```
/topicos
```

Body:

```json
{
  "titulo": "Erro ao usar Spring Boot",
  "mensagem": "Não consigo configurar o JPA",
  "idAutor": 1,
  "idCurso": 1
}
```

---

## Listar Tópicos

GET

```
/topicos
```

A listagem retorna apenas tópicos que **não estão desativados**.

Paginação disponível:

```
/topicos?page=0&size=10&sort=titulo
```

---

## Atualizar Tópico

PUT

```
/topicos/{id}
```

Body:

```json
{
  "titulo": "Novo título",
  "mensagem": "Mensagem atualizada",
  "idCurso": 2
}
```

---

## Concluir Tópico

PUT

```
/topicos/{id}/concluir
```

Altera o estado do tópico para **CONCLUIDO**.

---

## Desativar Tópico

DELETE

```
/topicos/{id}
```

Realiza o **soft delete** do tópico.

---

---

# Testes com Postman

Para testar a API utilizando Postman:

1. Realizar login para obter o token
2. Copiar o token retornado
3. Adicionar o token no header das requisições

Header:

```
Authorization: Bearer SEU_TOKEN
```

---

# Como Executar o Projeto

### 1. Clonar o repositório

```
git clone https://github.com/seu-usuario/forumhub.git](https://github.com/kaua3-c/Desafio_Alura_One_Conversor_De_Moeda.git
```

### 2. Configurar o banco de dados

Criar um banco PostgreSQL.

### 3. Configurar `application.properties`

```
spring.datasource.url=jdbc:postgresql://localhost:5432/forumhub
spring.datasource.username=postgres
spring.datasource.password=senha
```

### 4. Executar o projeto

```
mvn spring-boot:run
```
