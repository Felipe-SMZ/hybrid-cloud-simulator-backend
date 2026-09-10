# Hybrid Cloud Simulator

Protótipo acadêmico que simula métricas de interoperabilidade, latência e custos em uma arquitetura de nuvem híbrida (**on-premises + cloud**).

O backend é uma API REST que gera snapshots de métricas periodicamente e os persiste em um banco de dados relacional, permitindo que um dashboard frontend exiba a evolução desses indicadores ao longo do tempo.

Este projeto foi desenvolvido como parte de uma atividade da disciplina de **Computação em Nuvem**, com o objetivo de ilustrar os principais desafios técnicos e operacionais relacionados à adoção de arquiteturas de nuvem híbrida em grandes corporações.

## Tecnologias

* Java 21
* Spring Boot 4.x
* Spring Web
* Spring Data JPA
* Flyway
* PostgreSQL ou MySQL
* Maven

## Funcionalidades do MVP

* Dois ambientes pré-configurados:

    * `ON_PREM` — data center local fictício
    * `CLOUD` — nuvem pública fictícia
* Geração automática de snapshots de métricas a cada 5 segundos:

    * Latência (ms)
    * Taxa de erro de interoperabilidade (%)
    * Custo estimado por intervalo (BRL)
* API REST para:

    * Listar ambientes ativos
    * Consultar a última métrica de cada ambiente
    * Consultar o histórico de métricas em um determinado período

## Endpoints da API

### Ambientes

```http
GET /api/environments
```

Retorna a lista de ambientes ativos.

### Métricas

```http
GET /api/metrics/latest
```

Retorna o último snapshot de métricas para cada ambiente ativo.

```http
GET /api/metrics/history?from=2026-09-10T00:00:00Z&to=2026-09-10T02:00:00Z
```

Retorna o histórico de métricas no período informado, em ordem cronológica.

Os parâmetros `from` e `to` devem estar no formato ISO 8601:

```text
YYYY-MM-DDTHH:MM:SSZ
```

Caso nenhum período seja informado, o endpoint retorna as métricas das últimas 2 horas.

## Como rodar localmente

### Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

* JDK 21
* Maven 3.8+
* PostgreSQL ou MySQL

### 1. Clonar o repositório

```bash
git clone https://github.com/SEU_USUARIO/hybrid-cloud-simulator.git
cd hybrid-cloud-simulator
```

### 2. Criar o banco de dados

Crie um banco de dados vazio chamado `hybrid_cloud_simulator`.

#### PostgreSQL

```bash
createdb hybrid_cloud_simulator
```

Ou utilizando o SQL:

```sql
CREATE DATABASE hybrid_cloud_simulator;
```

#### MySQL

```sql
CREATE DATABASE hybrid_cloud_simulator;
```

### 3. Configurar as propriedades da aplicação

Por segurança, as configurações de acesso ao banco de dados **não devem ser commitadas no repositório**.

Na pasta:

```text
src/main/resources/
```

crie um arquivo chamado:

```text
application.properties
```

Adicione as configurações abaixo, ajustando o usuário e a senha de acordo com o seu ambiente local.

#### PostgreSQL

```properties
spring.application.name=hybrid-cloud-simulator

spring.datasource.url=jdbc:postgresql://localhost:5432/hybrid_cloud_simulator
spring.datasource.username=postgres
spring.datasource.password=sua_senha_aqui

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true

simulation.interval-ms=5000

server.error.include-message=never

# Perfil ativo (dev ou prod)
spring.profiles.active=dev
```

#### MySQL

Caso prefira utilizar MySQL, ajuste a URL e as configurações do datasource:

```properties
spring.application.name=hybrid-cloud-simulator

spring.datasource.url=jdbc:mysql://localhost:3306/hybrid_cloud_simulator?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=sua_senha_aqui

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true

simulation.interval-ms=5000

server.error.include-message=never

# Perfil ativo (dev ou prod)
spring.profiles.active=dev
```

### 4. Executar a aplicação

A partir da raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

Ou, caso esteja utilizando o Maven instalado no sistema:

```bash
mvn spring-boot:run
```

Após a inicialização, a aplicação estará disponível em:

```text
http://localhost:8080
```

### 5. Validar se a aplicação está funcionando

Com a aplicação em execução, teste os endpoints utilizando `curl`:

```bash
curl http://localhost:8080/api/environments
```

```bash
curl http://localhost:8080/api/metrics/latest
```

```bash
curl "http://localhost:8080/api/metrics/history?from=2026-09-10T00:00:00Z&to=2026-09-10T02:00:00Z"
```

Também é possível acessar os endpoints diretamente pelo navegador ou por ferramentas como Postman e Insomnia.

Após alguns segundos, verifique o banco de dados. A tabela `metric_snapshots` deverá estar recebendo novos registros automaticamente a cada intervalo configurado.

## Estrutura do projeto

```text
src/main/java/br/com/seunome/hybridcloudsimulator/
├── HybridCloudSimulatorApplication.java
├── config/
├── controller/
├── dto/
├── exception/
├── model/
├── repository/
└── service/
```

### Organização dos pacotes

* `model`: entidades JPA (`Environment`, `MetricSnapshot`) e o enum `EnvironmentType`.
* `repository`: interfaces do Spring Data JPA responsáveis pelo acesso aos dados.
* `service`: regras de negócio, simulação agendada e consultas.
* `controller`: endpoints da API REST.
* `dto`: objetos utilizados para entrada e saída de dados da API.
* `config`: configurações da aplicação, CORS, inicialização de dados e outras configurações.
* `exception`: tratamento centralizado de exceções e erros da API.

## Migrations do banco de dados

As migrations do banco ficam em:

```text
src/main/resources/db/migration/
```

A migration inicial:

```text
V1__create_initial_tables.sql
```

é responsável pela criação das tabelas:

* `environments`
* `metric_snapshots`

O **Flyway** é executado automaticamente durante a inicialização da aplicação e controla a versão das alterações realizadas no banco de dados.

## Próximos passos

Algumas evoluções planejadas para o projeto:

* Implementação do frontend utilizando React + Vite.
* Dashboard para visualização das métricas.
* Deploy do backend em uma plataforma de nuvem, como Render ou Railway.
* Deploy do frontend em uma plataforma de hospedagem estática, como Vercel ou Netlify.
* Implementação de cenários degradados para simulação.
* Criação de parâmetros ajustáveis para a simulação.
* Evolução das métricas e dos cenários de interoperabilidade.
* Adição de mecanismos de monitoramento e observabilidade.

## Licença

Este projeto foi desenvolvido para fins acadêmicos e de estudo.

Sinta-se à vontade para utilizá-lo como referência ou base para seus próprios projetos.
