# Hotel Reservation System

Este projeto é uma melhoria de um desafio técnico que realizei para uma empresa. Tive 4 dias para entregar o projeto original, mas não gostei do resultado, possivelmente devido ao tempo limitado ou ao escopo do desafio. Decidi refazer o projeto com algumas melhorias significativas.

## Descrição do Projeto

O projeto original continha apenas três tabelas: `customer`, `reservation` e `room`. Neste projeto melhorado, acrescentei uma nova tabela `employee` e implementei as seguintes melhorias:

- Adição do Swagger para documentação da API.
- Flyway para versionamento do banco de dados.
- Spring Security para segurança da aplicação.
- Testes unitários e de integração para garantir a qualidade do código.
- Adição do Docker Compose para subir o database PostgreSQL.
- Criação de uma imagem Docker da aplicação para fácil implantação.

## Funcionalidades

1. **Employee Management**:
    - Apenas funcionários do tipo `admin` podem criar outros funcionários (`common` e `admin`).
    - Funcionários do tipo `common` e `admin` podem registrar novos `customers`, `reservations` e `rooms`.

2. **Customer Management**:
    - Registro, atualização, busca e deleção de clientes.

3. **Room Management**:
    - Registro, atualização, busca e deleção de quartos.

4. **Reservation Management**:
    - Registro, atualização, busca e deleção de reservas.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para construção da aplicação.
- **Spring Security**: Implementação de segurança na aplicação.
- **Swagger**: Documentação da API disponível em `http://localhost:8080/swagger-ui/index.html#`.
- **Flyway**: Versionamento do banco de dados.
- **JUnit e Mockito**: Testes unitários e de integração.
- **PostgreSQL**: Banco de dados utilizado.
- **Docker e Docker Compose**: Configuração do ambiente de desenvolvimento e implantação com PostgreSQL.

## Como Executar o Projeto

### Pré-requisitos

- JDK 11 ou superior
- Maven
- Docker, Docker Compose

### Passos

1. Clone o repositório:
    ```bash
    git clone <URL-do-repositorio>
    cd hotel-reservation-system
    ```

2. Execute o Docker Compose:
    ```bash
    docker-compose up -d
    ```

3. Acesse a documentação da API no Swagger:
    - `http://localhost:8080/swagger-ui/index.html#`
  
4. Employee Admin:
    - email: admin@gmail.com
    - password: admin1234

## Estrutura do Banco de Dados

- **tb_employees**: Tabela para gerenciar os funcionários.
- **tb_customers**: Tabela para gerenciar os clientes.
- **tb_rooms**: Tabela para gerenciar os quartos.
- **tb_reservations**: Tabela para gerenciar as reservas.

## Contato

Para mais informações ou sugestões, entre em contato:

- Nome: Anderson Palmerim
- Email: [andersonp.viana.contato@gmail.com](mailto:andersonp.viana.contato@gmail.com)
- LinkedIn: [Anderson Palmerim](https://www.linkedin.com/in/andersonpdev/)
