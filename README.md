FórumHub — API REST (Spring Boot 3)

🚀 FórumHub é uma API REST desenvolvida em Java + Spring Boot 3 como parte do Challenge Back-End, inspirada no Fórum da Alura.
O projeto implementa um sistema de fórum onde usuários autenticados podem criar, consultar, atualizar e excluir tópicos associados a cursos, respeitando regras de segurança e autorização.

📌 Objetivo do Projeto

Replicar o funcionamento do fórum da Alura no nível de back-end, aplicando boas práticas de:

APIs REST

Segurança (Spring Security + JWT)

Persistência com banco relacional

Validações e regras de negócio

Versionamento com Git/GitHub

Organização e documentação profissional

🧠 Funcionalidades
🔐 Autenticação e Segurança

Login com usuário e senha

Senhas armazenadas com BCrypt

Autenticação via JWT (JSON Web Token)

Apenas usuários autenticados podem acessar a API

Apenas o autor do tópico pode editá-lo ou excluí-lo

📝 Gestão de Tópicos (CRUD)

Criar um novo tópico

Listar todos os tópicos (com paginação)

Detalhar um tópico específico

Atualizar um tópico

Excluir um tópico

⚠️ Regras de Negócio

Todos os campos são obrigatórios

Não é permitido cadastrar tópicos duplicados (mesmo título + mensagem)

Apenas o autor pode atualizar ou excluir seu tópico

Retornos HTTP corretos (201, 200, 204, 400, 401, 403, 404, 409)

🧱 Modelagem de Dados
Tópico

id

titulo

mensagem

dataCriacao

status (ABERTO | FECHADO)

autor (Usuário)

curso

Usuário

id

login

senha (hash)

nome

ativo

Curso

id

nome

categoria

🛠️ Stack Utilizada

Java 17

Spring Boot 3

Spring Web

Spring Data JPA

Spring Security

Validation (Bean Validation)

JWT (Auth0)

Flyway Migration

MySQL 8

Maven

Lombok

Insomnia / Postman

Git & GitHub

📂 Estrutura do Projeto

src/main/java/br/com/forumhub
 ├── controller
 ├── dto
 ├── domain
 │   ├── enums
 │   ├── Usuario.java
 │   ├── Curso.java
 │   └── Topico.java
 ├── repository
 ├── service
 └── infra
     ├── security
     └── exception

▶️ Como Executar o Projeto
Pré-requisitos

Java 17+

Maven 4+

MySQL 8+

Git

1️⃣ Clonar o repositório

git clone https://github.com/SEU_USUARIO/forumhub.git
cd forumhub

2️⃣ Criar o banco de dados
CREATE DATABASE forumhub;

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/forumhub
    username: root
    password: root
4️⃣ Rodar a aplicação
bash
Copiar código
mvn clean spring-boot:run
✔️ O Flyway criará automaticamente as tabelas.

🔑 Autenticação (JWT)
Login
POST /login

json
Copiar código
{
  "login": "usuario@email.com",
  "senha": "123456"
}
📤 Resposta:

json
Copiar código
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
➡️ Use o token no header das próximas requisições:

http
Copiar código
Authorization: Bearer SEU_TOKEN_AQUI
📬 Endpoints da API
Criar Tópico
POST /topicos

json
Copiar código
{
  "titulo": "Erro no Spring Boot",
  "mensagem": "Estou com erro ao subir a aplicação",
  "curso": "Spring Boot",
  "autorId": 1
}
📌 Retorna 201 Created

Listar Tópicos
GET /topicos?page=0&size=10

Detalhar Tópico
GET /topicos/{id}

Atualizar Tópico
PUT /topicos/{id}
✔️ Apenas o autor pode atualizar

Excluir Tópico
DELETE /topicos/{id}
✔️ Apenas o autor pode excluir
📌 Retorna 204 No Content

🧪 Testes
Os testes dos endpoints podem ser feitos utilizando:

Insomnia

Postman

Todos os endpoints protegidos exigem token JWT válido.

📋 Checklist (estilo Trello)
🟩 Concluído
Bootstrap do projeto

Configuração MySQL + Flyway

Modelagem das entidades

Repositório no GitHub

🟨 Em Desenvolvimento
Autenticação e JWT

CRUD de tópicos

Regras de autorização

⬜ Pronto para começar
Tratamento global de erros

README final com prints

Coleção Insomnia/Postman

🎯 Considerações Finais
Este projeto foi desenvolvido com foco em:

Boas práticas de back-end

Código limpo e organizado

Segurança e regras reais de negócio

Clareza para avaliação técnica e recrutamento
