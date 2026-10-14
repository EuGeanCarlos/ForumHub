# FórumHub

API Spring Boot com autenticação, filtro JWT e modelos de usuário, curso e tópico. O controlador disponível implementa autenticação; não há controlador CRUD de tópicos na branch analisada.

## Tecnologias

- Java
- Spring Boot
- Spring Security
- JPA
- Flyway
- Maven

## Funcionalidades e utilização

Configure banco e segredo JWT por variáveis de ambiente conforme application.properties. Consulte controller/AuthController.java para o contrato de login.

## Como executar

```bash
git clone https://github.com/EuGeanCarlos/ForumHub.git
cd ForumHub
mvn test
mvn spring-boot:run
```

Requer JDK compatível com pom.xml e Maven 3.9+. Testes Spring podem exigir banco/serviços locais configurados.

## Demonstração e publicação

Execução local. Nenhuma demonstração nova foi confirmada nesta etapa.

GitHub Pages hospeda arquivos estáticos e não executa Java, Node.js, PHP ou Python. As dependências de servidor deste projeto devem ser executadas em ambiente apropriado.

## Estrutura versionada

```text
.gitattributes
.gitignore
.mvn/wrapper/maven-wrapper.properties
README.md
mvnw
mvnw.cmd
pom.xml
src/main/java/br/com/forumhub/ForumhubApplication.java
src/main/java/br/com/forumhub/controller/AuthController.java
src/main/java/br/com/forumhub/domain/Curso.java
src/main/java/br/com/forumhub/domain/Topico.java
src/main/java/br/com/forumhub/domain/Usuario.java
src/main/java/br/com/forumhub/domain/enums/StatusTopico.java
src/main/java/br/com/forumhub/dto/LoginRequestDTO.java
src/main/java/br/com/forumhub/dto/TokenResponseDTO.java
src/main/java/br/com/forumhub/infra/security/JwtFilter.java
src/main/java/br/com/forumhub/infra/security/PasswordHashGenerator.java
src/main/java/br/com/forumhub/infra/security/SecurityConfig.java
src/main/java/br/com/forumhub/infra/security/TokenService.java
src/main/java/br/com/forumhub/repository/CursoRepository.java
src/main/java/br/com/forumhub/repository/TopicoRepository.java
src/main/java/br/com/forumhub/repository/UsuarioRepository.java
…
```

## Status

Protótipo / projeto de estudo em desenvolvimento. A descrição acima distingue o que está implementado das dependências ou etapas pendentes.

## Autor e créditos

**Gean Carlos** — estudante de Engenharia de Computação, UEMA.

[GitHub](https://github.com/EuGeanCarlos)

Licenças, autoria compartilhada e atribuições existentes no código e nos materiais originais permanecem aplicáveis.

---

## Documentação original e contexto

O material original a seguir preserva contexto, créditos e planejamento. Para execução e limitações atuais, considere as seções de auditoria acima.

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

## Validação desta auditoria — 14/10/2026

Sem publicação nova confirmada. mvn test falhou no carregamento do contexto. A conexão com MySQL local foi recusada; o servidor de banco necessário não estava disponível. A integração com banco não foi validada. Há valores de configuração sensíveis fixos que exigem revisão e rotação se reais.

## Endpoint implementado

`POST /login` recebe as credenciais conforme `LoginRequestDTO` e retorna o token definido por `TokenResponseDTO`. As entidades de cursos e tópicos ainda não possuem controlador CRUD nesta branch. O banco configurado é MySQL.
