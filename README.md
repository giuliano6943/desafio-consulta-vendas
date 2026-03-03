# 📊 Desafio - Consulta de Vendas

Projeto desenvolvido como parte do **Módulo JPA, consultas SQL e JPQL** do curso **Java Spring Professional**, ministrado pelo professor **Nélio Alves**.

O objetivo foi implementar uma API REST para consultas de vendas, utilizando **Spring Boot**, **Spring Data JPA** e **JPQL**, com foco em relatórios detalhados e sumários de vendas por vendedor.

---

## 🚀 Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- JPQL / SQL Nativo  
- H2 Database  
- Maven  

---

## 📂 Estrutura do Projeto

O projeto segue arquitetura em camadas:

### Entities
- `Sale`
- `Seller`

### DTOs
- `SaleMinDTO` → Relatório detalhado  
- `SaleSummaryDTO` → Sumário de vendas  

### Projections
- `SaleSummaryProjection` → Interface para retorno otimizado do total por vendedor  

### Repositories
- Consultas com JPQL  
- Uso de Projections  
- Filtros dinâmicos por data e nome  

### Services
- Regras de negócio  
- Tratamento de datas padrão (últimos 12 meses)

### Controllers
- Endpoints REST  

---

## 📌 Endpoints

### 🔎 1. Relatório de Vendas

`GET /sales/report`

Retorna o relatório detalhado de vendas.

### Parâmetros opcionais:
- `minDate` (yyyy-MM-dd)
- `maxDate` (yyyy-MM-dd)
- `name` (nome parcial do vendedor)

### Exemplo:
```
/sales/report?minDate=2022-01-01&maxDate=2022-06-30&name=Thor
```

Se nenhuma data for informada, o sistema considera automaticamente os **últimos 12 meses**.

---

### 📈 2. Sumário de Vendas por Vendedor

`GET /sales/summary`

Retorna o total de vendas agrupado por vendedor.

### Parâmetros opcionais:
- `minDate` (yyyy-MM-dd)
- `maxDate` (yyyy-MM-dd)

### Exemplo:
```
/sales/summary?minDate=2022-01-01&maxDate=2022-06-30
```

Se nenhuma data for informada, o sistema considera automaticamente os **últimos 12 meses**.

---

## 📊 Exemplos de Resposta

### `/sales/summary?minDate=2022-01-01&maxDate=2022-06-30`

```json
{
  "content": [
    { "sellerName": "Anakin", "total": 110571.0 },
    { "sellerName": "Logan", "total": 83587.0 },
    { "sellerName": "Loki Odinson", "total": 150597.0 },
    { "sellerName": "Padme", "total": 135902.0 },
    { "sellerName": "Thor Odinson", "total": 144896.0 }
  ]
}
```

---

### `/sales/summary` (últimos 12 meses)

```json
{
  "content": [
    { "sellerName": "Anakin", "total": 71698.0 },
    { "sellerName": "Logan", "total": 71326.0 },
    { "sellerName": "Loki Odinson", "total": 101080.0 },
    { "sellerName": "Padme", "total": 77014.0 },
    { "sellerName": "Thor Odinson", "total": 99971.0 }
  ]
}
```

---

## 🛠️ Como Executar o Projeto

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/giuliano6943/desafio-consulta-vendas.git
```

### 2️⃣ Acesse a pasta do projeto

```bash
cd desafio-consulta-vendas
```

### 3️⃣ Execute com Maven

```bash
mvn spring-boot:run
```

### 4️⃣ Acesse os endpoints

- http://localhost:8080/sales/report  
- http://localhost:8080/sales/summary  

---

## 🎯 Objetivo do Desafio

- Praticar consultas com JPQL e SQL usando Spring Data JPA  
- Implementar filtros dinâmicos de datas e nomes  
- Utilizar DTOs e Projections para diferentes formatos de resposta  
- Trabalhar com paginação  
- Consolidar o aprendizado de JPA em aplicações reais  

---

## 👨‍💻 Autor

Giuliano D'Agosto Neto  

Projeto desenvolvido durante o curso **Java Spring Professional** do professor **Nélio Alves**.
