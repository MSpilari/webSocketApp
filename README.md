# 📸 WebSocket Photo Album - Fullstack App (Angular + Spring Boot + AWS + WebSocket)

## 🌍 Sobre o Projeto | About the Project

**PT-BR:**  
Este projeto fullstack é uma aplicação de gerenciamento de álbuns de fotos em tempo real. Ele utiliza **Spring Boot** no backend e **Angular** no frontend. A comunicação entre cliente e servidor é feita com **WebSockets**, e os dados são armazenados utilizando os serviços **S3** e **DynamoDB** da AWS, simulados localmente com **LocalStack**.

Futuramente, a infraestrutura será provisionada com **Terraform**, adotando uma abordagem escalável e sustentável com Infrastructure as Code (IaC).

**EN:**  
This fullstack project is a real-time photo album management app. It uses **Spring Boot** for the backend and **Angular** for the frontend. Client-server communication happens via **WebSockets**, and data is stored using **AWS S3** and **DynamoDB**, simulated locally using **LocalStack**.

In the near future, the infrastructure will be provisioned with **Terraform**, embracing a scalable and maintainable **Infrastructure as Code (IaC)** approach.

---

## 🛠️ Tecnologias | Technologies

### Backend

- Java 21+ & Spring Boot
- WebSocket (STOMP + SockJS)
- AWS SDK: S3 & DynamoDB
- LocalStack (via Docker)
- SLF4J for logging

### Frontend

- Angular
- TypeScript
- RxJS + WebSocket API
- Angular Material (opcional)

### Infra

- Docker + Docker Compose
- LocalStack
- Terraform (em breve)

---

## 📁 Estrutura do Projeto | Project Structure

```
project-root/
│
├── backend/           # Projeto Spring Boot
├── frontend/          # Projeto Angular
└── docker-compose.yml # Configuração do LocalStack
```

---

## ⚙️ Executando Localmente | Running Locally

### 🔧 Requisitos | Requirements

- Java 21+
- Node.js + Angular CLI
- Docker + Docker Compose
- Terraform CLI (futuramente)

---

### 🚀 Backend (Spring Boot + LocalStack)

1. Suba os containers com LocalStack:

   ```bash
   docker-compose up
   ```

2. Inicie o backend:
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

> **Nota:** A criação de buckets S3 e tabelas DynamoDB via código é feita **apenas para fins educacionais**, com o objetivo de aprender a integrar a AWS com o Spring Boot.  
> Em ambientes reais, a abordagem mais indicada é criar esses recursos com uma ferramenta de **Infrastructure as Code**, como **Terraform**, e apenas conectar-se a eles via aplicação.

---

### 🖥️ Frontend (Angular)

1. Instale as dependências:

   ```bash
   cd frontend
   npm install
   ```

2. Inicie o servidor de desenvolvimento:
   ```bash
   ng serve
   ```

---

## 🔌 WebSockets

A aplicação utiliza **WebSockets** para atualizações em tempo real. Ao adicionar ou modificar um álbum, todos os usuários conectados recebem as mudanças instantaneamente, sem necessidade de recarregar a página.

---

## 📦 Terraform (em breve)

A criação de recursos como buckets S3 e tabelas DynamoDB será migrada para **Terraform** para garantir versionamento, reprodutibilidade e uma infraestrutura mais sólida e segura.

---
