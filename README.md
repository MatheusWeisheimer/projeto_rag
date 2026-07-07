# Projeto RAG - Shakespeare

Chatbot RAG (Quarkus + LangChain4j) sobre as obras de Shakespeare.

## Pré-requisitos

- JDK 21+
- Docker e Docker Compose
- Git LFS (`git lfs install`)
- Chave de API da OpenAI com quota disponível

## Setup

1. Clonar o repositório e baixar os PDFs versionados via LFS:
   ```
   git lfs pull
   ```

2. Copiar `.env.example` para `.env` e preencher:
   ```
   OPENAI_API_KEY=...
   QUARKUS_DATASOURCE_USERNAME=postgres
   QUARKUS_DATASOURCE_PASSWORD=password
   QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://localhost:5432/postgres
   ```
   Os valores padrão de usuário/senha/porta já batem com o `compose.yaml`.

3. Subir o Postgres com pgvector:
   ```
   docker compose up -d
   ```
   Garanta que a porta 5432 não esteja em uso por outro serviço/container.

## Rodando a aplicação

```
set -a && source .env && set +a
./mvnw quarkus:dev
```

A aplicação fica disponível em `http://localhost:8080`.

Obs: ao iniciar, a ingestão dos PDFs demorou bastante para rodar.
