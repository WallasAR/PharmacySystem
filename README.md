# PharmacySystem

Sistema desktop em **JavaFX + MySQL** para gerenciamento de farmacia (clientes, funcionarios, medicamentos, carrinho, encomendas e registros de compra).

## Stack do projeto

- Java 21
- JavaFX (via Maven)
- Maven Wrapper (`mvnw` / `mvnw.cmd`)
- MySQL 8.0
- Docker + Docker Compose (para subir o banco localmente)

## Estrutura principal

- `src/main/java/com/example/guitest`: telas e controllers JavaFX
- `src/main/java/com/db/bank/Banco.java`: conexao JDBC e operacoes SQL
- `src/main/java/com/system/login/SistemaLogin.java`: autenticacao
- `src/main/resources/com/example/guitest`: FXML, CSS e imagens da interface
- `docker-compose.yml`: servico de banco no Docker
- `docker/mysql/init/01-schema.sql`: criacao inicial do schema e tabelas

## Como rodar (passo a passo completo)

### 1) Pre-requisitos

Instale:

- **JDK 21**
- **Docker Desktop** (com Docker Compose habilitado)
- **IntelliJ IDEA** (recomendado para executar app JavaFX)

Verifique no terminal:

```powershell
java -version
docker --version
docker compose version
```

### 2) Subir o banco no Docker

Na raiz do projeto:

```powershell
docker compose up -d
```

Esse comando cria:

- banco `farmacia`
- usuario `adm`
- senha `1234`
- tabelas usadas pelo sistema
- usuario administrativo inicial para login:
  - usuario: `admin`
  - senha: `admin123`

Para checar se o banco ficou saudavel:

```powershell
docker compose ps
```

Se quiser acompanhar logs:

```powershell
docker compose logs -f db
```

### 3) Configurar e executar a aplicacao JavaFX

> O projeto foi estruturado para uso em IDE. O fluxo mais estavel atualmente e abrir no IntelliJ e executar a classe principal.

1. Abra a pasta do projeto no IntelliJ.
2. Aguarde o Maven sincronizar as dependencias.
3. Garanta que o SDK do projeto esteja em **Java 21**.
4. Execute a classe principal: `com.example.guitest.Main`.

### 4) Credenciais e fluxo de acesso

- **Administrador inicial**:
  - usuario: `admin`
  - senha: `admin123`
- **Funcionario**:
  - faz login com `usuario` e `senha` cadastrados na tabela `funcionarios`.

## Comandos uteis de banco

Parar containers:

```powershell
docker compose down
```

Parar e remover dados (reset completo do banco):

```powershell
docker compose down -v
```

Subir novamente depois de reset:

```powershell
docker compose up -d
```

## Conexao JDBC usada pela aplicacao

Atualmente o codigo conecta com:

- URL: `jdbc:mysql://localhost:3306/farmacia?serverTimezone=America/Sao_Paulo`
- Usuario: `adm`
- Senha: `1234`

Esses valores estao em:

- `src/main/java/com/db/bank/Banco.java`
- `src/main/java/com/system/login/SistemaLogin.java`

## Troubleshooting

- **Erro de conexao com banco**
  - confirme que o container `pharmacy-mysql` esta `Up` (`docker compose ps`)
  - confirme que a porta `3306` nao esta ocupada por outro MySQL local

- **Banco subiu mas sem tabelas**
  - rode `docker compose down -v` e depois `docker compose up -d`
  - o script `docker/mysql/init/01-schema.sql` so executa no primeiro bootstrap do volume

- **Falha ao abrir telas**
  - confirme JDK 21 no projeto
  - confira se o IntelliJ terminou a importacao Maven

## Observacoes importantes

- O projeto possui artefatos compilados em `target/`; para execucao local isso nao e necessario.
- O banco no Docker foi configurado para reproduzir os nomes de schema/tabelas esperados pelo codigo existente.
