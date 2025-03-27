# 🚀 Guia Rápido para Rodar o Projeto

## ✅ Pré-requisitos

As seguintes ferramentas devem estar instaladas:

- Docker e Docker Compose
- Java (JDK 17, por exemplo)
- Maven

---

## 🐳 1. Subir o ambiente com Docker Compose

O arquivo `docker-compose.yaml` define os serviços necessários, como o banco de dados.

### Subir os containers:

```bash
docker-compose up -d
```

### Verificar os containers ativos:

```bash
docker ps
```

---

## ☕ 2. Compilar e Executar o Projeto Java com Maven

### Navegue até a raiz do projeto (onde está o `pom.xml`):

```bash
cd caminho/do/projeto
```

### Compilar o projeto:

```bash
mvn clean install
```

### Executar a aplicação:

```bash
mvn exec:java
```

> Caso o `pom.xml` não tenha o plugin `exec`, você pode executar manualmente com:

```bash
java -cp target/seu-jar-gerado.jar caminho.da.sua.Main
```

---

## 🧪 3. Acessar o banco de dados com pgAdmin (opcional)

O pgAdmin estará disponível em: [http://localhost:5050](http://localhost:5050)

### Para conectar ao PostgreSQL no pgAdmin:

- **Host**: nome do serviço do banco no `docker-compose.yaml` (ex: `db`)
- **Porta**: 5432 (ou a configurada no compose)
- **Usuário** e **Senha**: definidos nas variáveis de ambiente do serviço do banco
- **Database**: nome do banco criado no serviço PostgreSQL

---

## 🧹 4. Parar os containers

```bash
docker-compose down
```
