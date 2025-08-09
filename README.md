# Spring-Boot-UFBA-projeto

Projeto de conclusão do curso de programação backend com Spring Boot / Spring Boot backend programming course conclusion project

# PARA QUESTÕES DE AVALIAÇÃO (IMPORTANTE)

PARA TER ACESSO A TODAS AS FUNCIONALIDADES VOCE PRECISA ESTAR LOGADO COMO ADMIN, PARA FAZER ISSO SIGA O PASSO A PASSO

1.acesse localhost:8080/login com metodo POST e coloque no body o seguinte(JSON):
{
    "username": "admin",
    "password": "123"
}
vai ser retornado para voce algo como:
{
    "accessToken": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJteWJhY2tlbmQiLCJzdWIiOiI0ZGEyYzliYy0zNzhkLTRhNzMtOTM4YS1mYzQxNGFjN2I1N2YiLCJleHAiOjE3NTQ3NTc3OTMsInNjb3BlIjoiYWRtaW4ifQ.avBFFMmbS_XoahQQSnnYlX7jq9N6XcmECB8to0nctduTwDru7SDf_ItS3HKl8-lJP1el9hq6ePoXQ08iOLTj27cb1B7JuLjP7_Eb0JRcEqJOydRrY-3mBxnaS5gFvKvqSMq1dtCdRyl7QW2JI6OIIIkq5eiwPusl1vuxLfbGPjiQN4yGOBF4ud_tfJCXwyvmitY0E-N4hz2JtOg4KwvEfBTbXxCHIw_ypaG0UEa3iFxOeID3jRtIFfSTx1ujxHln30Vk13Rd1S5OYSzyXrLABK4Rqray-1SxF_FssU0uw1BgZWZcHbymtYsO3IxkGicA3RLx5h4g47BIB2BeE_u31A",
    "expiresIn": 300
}
esse monte de numeros e letras é o seu token.
2.copie o seu token e para cada requisição em todos os endpoints vc vai colocar no seu header o seguinte:
em KEY voce vai colocar Authorization e em value voce vai colocar Bearer token
vai ficar algo mais ou menos assim
Authorization | Baerer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJteWJhY2tlbmQiLCJzdWIiOiI0ZGEy...
apos isso voce pode testar o endpoints

# API de Restaurante

## 📝 Descrição

API RESTful completa para o gerenciamento de um restaurante, desenvolvida com **Spring Boot**. O projeto segue as melhores práticas de desenvolvimento, com uma arquitetura bem definida em camadas, sistema de segurança robusto com JWT e documentação interativa com Swagger.

## ✨ Funcionalidades

  * **Gerenciamento de Entidades:** CRUD completo para Pratos, Categorias, Clientes e Pedidos.
  * **Segurança:** Sistema de autenticação via JWT e autorização baseada em papéis (Roles).
  * **Relacionamentos:** Associação de pratos a categorias.
  * **Documentação:** API documentada com Swagger (OpenAPI) para fácil visualização e teste dos endpoints.
  * **Tratamento de Exceções:** Respostas de erro padronizadas para uma melhor experiência do cliente da API.

-----

## 🏗️ Estrutura do Projeto

O projeto segue uma arquitetura em camadas para garantir a separação de responsabilidades e a manutenibilidade do código:

  * **`config`**: Classes de configuração do Spring, como `SecurityConfig` (para Spring Security), `SwaggerAdvancedConfig` (para a documentação) e `AdminUserConfig` (para criar um usuário administrador padrão na inicialização).
  * **`controllers`**: Camada de API, responsável por expor os endpoints REST e receber as requisições HTTP.
  * **`services`**: Camada de serviço, onde reside a lógica de negócio da aplicação.
  * **`repository`**: Camada de acesso a dados (DAO), utilizando Spring Data JPA para interagir com o banco de dados.
  * **`entity`**: Classes que modelam as tabelas do banco de dados (Entidades JPA).
  * **`DTO`**: (Data Transfer Objects) Objetos que definem a estrutura de dados para a comunicação com a API, evitando a exposição direta das entidades.
  * **`exception`**: Classes para o tratamento de exceções globais, permitindo respostas de erro padronizadas.
  * **`mapper`**: Utilitários para converter (mapear) objetos entre as camadas (ex: de DTO para Entity).

-----

## 🚀 Tecnologias Utilizadas

  * **Java 17**
  * **Spring Boot**: Framework principal para construção da aplicação.
  * **Spring Security**: Para implementação da segurança e autenticação.
  * **Spring Data JPA / Hibernate**: Para persistência de dados e mapeamento objeto-relacional.
  * **Maven**: Gerenciador de dependências e build do projeto.
  * **Swagger (OpenAPI 3)**: Para documentação da API.
  * **JWT (JSON Web Token)**: Para autenticação stateless.

-----

## ⚙️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:

  * [JDK](https://www.oracle.com/java/technologies/downloads/) (versão 17 ou superior)
  * [Maven](https://maven.apache.org/download.cgi)
  * Um banco de dados de sua preferência (a aplicação está configurada para usar um banco em memória como o H2, mas pode ser alterado no `application.properties`).

-----

## 🏃‍♀️ Como Executar a Aplicação

1.  **Clone o repositório:**

    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    ```

2.  **Acesse o diretório do projeto:**

    ```bash
    cd restaurante-api
    ```

3.  **(Opcional) Configure o banco de dados:**
    Para usar um banco de dados externo (como PostgreSQL, MySQL), altere o arquivo `src/main/resources/application.properties` com as suas credenciais.

4.  **Execute a aplicação via Maven:**

    ```bash
    mvn spring-boot:run
    ```

5.  **Acesse a API:**

      * **Base URL:** `http://localhost:8080`
      * **Swagger UI (Documentação):** `http://localhost:8080/swagger-ui.html`

    > ℹ️ **Nota:** Um usuário **administrador** padrão é criado na inicialização da aplicação pela classe `AdminUserConfig`. Verifique o código para obter as credenciais.

-----

## 🛡️ Segurança e Autenticação

A API utiliza **Spring Security** para gerenciar a autenticação e autorização.

  * **Autenticação:** O endpoint `POST /login` valida as credenciais do usuário (`username` e `password`) e, em caso de sucesso, gera um **Token JWT** que deve ser enviado no cabeçalho `Authorization` das requisições subsequentes como `Bearer <token>`.
  * **Autorização:** O acesso aos endpoints é controlado por papéis (Roles).
      * `SCOPE_ADMIN`: Acesso total ao sistema.
      * `SCOPE_BASIC`: Acesso limitado, ideal para clientes ou usuários comuns.

-----

## 🔀 Relacionamentos entre Entidades

```mermaid
erDiagram
    USER {
        UUID userId PK
        string username
        string password
    }
    ROLE {
        long roleId PK
        string name
    }
    USER ||--|{ ROLE : has
    CLIENTE {
        long id PK
        string nome
        string telefone
        string endereco
    }
    PEDIDO {
        long id PK
        long cliente_id FK
        UUID user_id FK
        string status
        decimal total
        datetime dataHora
    }
    PEDIDO ||--o{ CLIENTE : "realizado por"
    PEDIDO ||--o{ USER : "registrado por"
    CATEGORIA {
        long id PK
        string nome
        string descricao
    }
    PRATO {
        long id PK
        string nome
        string descricao
        decimal preco
        int tempoDePreparo
    }
    CATEGORIA ||--|{ PRATO : "contém"

```

-----

## 📦 Modelos de Dados (DTOs) e Endpoints

A comunicação com a API é feita através de DTOs. A seguir, a lista de endpoints agrupados por recurso.

<details>
<summary><b>Prato</b></summary>
Atributo	Tipo	Descrição
id	Long	Identificador único do prato.
nome	String	Nome do prato.
descricao	String	Descrição detalhada do prato.
preco	BigDecimal	Preço do prato.
tempoDePreparo	Integer	Tempo estimado de preparo em minutos.

</details>

<details>
<summary><b>Categoria</b></summary>
Atributo	Tipo	Descrição
id	Long	Identificador único da categoria.
nome	String	Nome da categoria.
descricao	String	Descrição da categoria.

</details>

<details>
<summary><b>Cliente</b></summary>
Atributo	Tipo	Descrição
id	Long	Identificador único do cliente.
nome	String	Nome do cliente.
telefone	String	Telefone de contato do cliente.
endereco	String	Endereço do cliente.

</details>

<details>
<summary><b>Pedido</b></summary>
Atributo	Tipo	Descrição
id	Long	Identificador único do pedido.
cliente	Cliente	Cliente que realizou o pedido.
user	User	Usuário que registrou o pedido.
status	StatusPedido	Status atual do pedido (PENDENTE, EM_PREPARO, PRONTO, ENTREGUE).
total	BigDecimal	Valor total do pedido.
dataHora	LocalDateTime	Data e hora em que o pedido foi realizado.

</details>

<details>
<summary><b>User</b></summary>
Atributo	Tipo	Descrição
userId	UUID	Identificador único do usuário.
username	String	Nome de usuário para login.
password	String	Senha do usuário (armazenada com hash).
roles	Set<Role>	Papéis do usuário (BASIC, ADMIN).

</details>

<details>
<summary><b>Role</b></summary>
Atributo	Tipo	Descrição
roleId	Long	Identificador único do papel.
name	String	Nome do papel (BASIC, ADMIN).

</details>

Endpoints da API

A seguir estão os endpoints disponíveis na API.

🔑 Autenticação

<details>
<summary><code>POST /login</code> - <b>Autenticar usuário</b></summary>

Realiza a autenticação do usuário e retorna um token JWT.

Request Body:
JSON

{
  "username": "seu_usuario",
  "password": "sua_senha"
}

Response 200 (OK):
JSON

{
  "accessToken": "seu_token_jwt",
  "expiresIn": 300
}

Response 401 (Unauthorized):
JSON

{
  "message": "usuario ou senha invalido"
}

</details>

👤 Usuários

<details>
<summary><code>POST /users</code> - <b>Criar um novo usuário</b></summary>

Cria um novo usuário com o papel BASIC.

Request Body:
JSON

{
  "userName": "novo_usuario",
  "password": "senha_forte"
}

Response 200 (OK): Corpo da resposta vazio.

Response 422 (Unprocessable Entity): Se o nome de usuário já existir.

</details>

<details>
<summary><code>GET /users</code> - <b>Listar todos os usuários</b></summary>

Retorna uma lista de todos os usuários cadastrados.

🔒 Requer autorização: ADMIN

Response 200 (OK):
JSON

[
  {
    "userId": "uuid-do-usuario",
    "username": "nome_do_usuario",
    "roles": [
      {
        "roleId": 2,
        "name": "BASIC"
      }
    ]
  }
]

</details>

🧑‍🤝‍🧑 Clientes

<details>
<summary><code>GET /cliente</code> - <b>Listar todos os clientes</b></summary>

Retorna uma lista com todos os clientes.

🔒 Requer autorização: ADMIN

Response 200 (OK): List<ClienteDTO>

</details>

<details>
<summary><code>GET /cliente/buscarPorId/{id}</code> - <b>Buscar cliente por ID</b></summary>

Busca um cliente específico pelo seu ID.

🔒 Requer autorização: ADMIN

Parâmetros:

    id (path): Long - ID do cliente.

Response 200 (OK): ClienteDTO

Response 404 (Not Found): Se o cliente não for encontrado.

</details>

<details>
<summary><code>POST /cliente</code> - <b>Criar um novo cliente</b></summary>

Cria um novo cliente.

Request Body: ClienteDTO

Response 201 (Created): ClienteDTO

</details>

<details>
<summary><code>PUT /cliente/{id}</code> - <b>Atualizar um cliente</b></summary>

Atualiza os dados de um cliente existente.

Parâmetros:

    id (path): Long - ID do cliente.

Request Body: ClienteDTO

Response 200 (OK): ClienteDTO

Response 404 (Not Found): Se o cliente não for encontrado.

</details>

<details>
<summary><code>DELETE /cliente/{id}</code> - <b>Deletar um cliente</b></summary>

Exclui um cliente.

🔒 Requer autorização: ADMIN

Parâmetros:

    id (path): Long - ID do cliente.

Response 204 (No Content): Cliente deletado com sucesso.

Response 404 (Not Found): Se o cliente não for encontrado.

</details>

🏷️ Categorias

<details>
<summary><code>GET /categoria</code> - <b>Listar todas as categorias</b></summary>

Retorna uma lista de todas as categorias.

Response 200 (OK): List<CategoriaDTO>

</details>

<details>
<summary><code>GET /categoria/{id}</code> - <b>Buscar categoria por ID</b></summary>

Busca uma categoria específica pelo seu ID.

Parâmetros:

    id (path): Long - ID da categoria.

Response 200 (OK): CategoriaDTO

Response 404 (Not Found): Se a categoria não for encontrada.

</details>

<details>
<summary><code>POST /categoria</code> - <b>Criar uma nova categoria</b></summary>

Cria uma nova categoria.

Request Body: CategoriaDTO

Response 201 (Created): CategoriaDTO

</details>

<details>
<summary><code>PUT /categoria/{id}</code> - <b>Atualizar uma categoria</b></summary>

Atualiza os dados de uma categoria existente.

Parâmetros:

    id (path): Long - ID da categoria.

Request Body: CategoriaDTO

Response 200 (OK): CategoriaDTO

Response 404 (Not Found): Se a categoria não for encontrada.

</details>

<details>
<summary><code>DELETE /categoria/{id}</code> - <b>Deletar uma categoria</b></summary>

Exclui uma categoria.

Parâmetros:

    id (path): Long - ID da categoria.

Response 204 (No Content): Categoria deletada com sucesso.

Response 404 (Not Found): Se a categoria não for encontrada.

</details>

<details>
<summary><code>POST /categoria/{idCategoria}/prato/{idPrato}</code> - <b>Adicionar prato a uma categoria</b></summary>

Associa um prato existente a uma categoria.

Parâmetros:

    idCategoria (path): Long - ID da categoria.

    idPrato (path): Long - ID do prato.

Response 200 (OK): CategoriaDTO com o prato adicionado.

Response 404 (Not Found): Se a categoria ou o prato não forem encontrados.

</details>

<details>
<summary><code>GET /categoria/{id}/pratos</code> - <b>Listar pratos de uma categoria</b></summary>

Retorna todos os pratos associados a uma categoria específica.

Parâmetros:

    id (path): Long - ID da categoria.

Response 200 (OK): List<PratoDTO>

Response 404 (Not Found): Se a categoria não for encontrada.

</details>

🍕 Pratos

<details>
<summary><code>GET /prato</code> - <b>Listar todos os pratos</b></summary>

Retorna uma lista de todos os pratos.

Response 200 (OK): List<PratoDTO>

</details>

<details>
<summary><code>GET /prato/{id}</code> - <b>Buscar prato por ID</b></summary>

Busca um prato específico pelo seu ID.

Parâmetros:

    id (path): Long - ID do prato.

Response 200 (OK): PratoDTO

Response 404 (Not Found): Se o prato não for encontrado.

</details>

<details>
<summary><code>POST /prato</code> - <b>Criar um novo prato</b></summary>

Cria um novo prato.

Request Body: PratoDTO

Response 201 (Created): PratoDTO

</details>

<details>
<summary><code>PUT /prato/{id}</code> - <b>Atualizar um prato</b></summary>

Atualiza os dados de um prato existente.

Parâmetros:

    id (path): Long - ID do prato.

Request Body: PratoDTO

Response 200 (OK): PratoDTO

Response 404 (Not Found): Se o prato não for encontrado.

</details>

<details>
<summary><code>DELETE /prato/{id}</code> - <b>Deletar um prato</b></summary>

Exclui um prato.

Parâmetros:

    id (path): Long - ID do prato.

Response 204 (No Content): Prato deletado com sucesso.

Response 404 (Not Found): Se o prato não for encontrado.

</details>

📝 Pedidos

<details>
<summary><code>GET /pedido</code> - <b>Listar todos os pedidos</b></summary>

Retorna uma lista de todos os pedidos.

Response 200 (OK): List<PedidoDTO>

</details>

<details>
<summary><code>GET /pedido/{id}</code> - <b>Buscar pedido por ID</b></summary>

Busca um pedido específico pelo seu ID.

Parâmetros:

    id (path): Long - ID do pedido.

Response 200 (OK): PedidoDTO

Response 404 (Not Found): Se o pedido não for encontrado.

</details>

<details>
<summary><code>POST /pedido</code> - <b>Criar um novo pedido</b></summary>

Cria um novo pedido.

Request Body: PedidoDTO

Response 201 (Created): PedidoDTO

</details>

<details>
<summary><code>PUT /pedido/{id}</code> - <b>Atualizar um pedido</b></summary>

Atualiza os dados de um pedido existente.

Parâmetros:

    id (path): Long - ID do pedido.

Request Body: PedidoDTO

Response 200 (OK): PedidoDTO

Response 404 (Not Found): Se o pedido não for encontrado.

</details>

<details>
<summary><code>DELETE /pedido/{id}</code> - <b>Deletar um pedido</b></summary>

Exclui um pedido.

Parâmetros:

    id (path): Long - ID do pedido.

Response 204 (No Content): Pedido deletado com sucesso.

Response 404 (Not Found): Se o pedido não for encontrado.

</details>
