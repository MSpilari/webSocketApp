# 📸 WebSocket Photo Album - Fullstack App (React + Vite + Spring Boot + AWS + WebSocket)

## 🌍 Sobre o Projeto | About the Project

**PT-BR:**  
Este projeto fullstack é uma aplicação de gerenciamento de álbuns de fotos em tempo real. Ele utiliza **Spring Boot** no backend e **React** com **Vite** no frontend. A comunicação entre cliente e servidor é feita com **WebSockets**, e os dados são armazenados utilizando os serviços **S3** e **DynamoDB** da AWS, simulados localmente com **LocalStack**.

Futuramente, a infraestrutura será provisionada com **Terraform**, adotando uma abordagem escalável e sustentável com Infrastructure as Code (IaC).

**EN:**  
This fullstack project is a real-time photo album management app. It uses **Spring Boot** for the backend and **React** with **Vite** for the frontend. Client-server communication happens via **WebSockets**, and data is stored using **AWS S3** and **DynamoDB**, simulated locally using **LocalStack**.

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

- React
- Vite
- TypeScript
- WebSocket API
- Tailwind CSS (opcional)

### Infra

- Docker + Docker Compose
- LocalStack
- Terraform (em breve)

---

## 📁 Estrutura do Projeto | Project Structure

```
project-root/
│
├── webSocketBackend/           # Projeto Spring Boot
├── socketFrontend/          # Projeto React + Vite
└── docker-compose.yml # Configuração do LocalStack
```

---

## ⚙️ Executando Localmente | Running Locally | Development Mode

### 🔧 Requisitos | Requirements

- Java 21+
- Node.js + npm
- Docker + Docker Compose
- Terraform CLI (futuramente)

---

### 🚀 Backend (Spring Boot + LocalStack)

1. Suba o contêiner com LocalStack | Get Localstack up and running:

   ```bash
   cd webSocketBackend
   docker-compose up -d
   ```

2. Inicie o backend | Init Spring Boot:

   ```bash
   ./mvnw spring-boot:run
   ```

   PT-BR:

   > **Nota:** A criação de buckets S3 e tabelas DynamoDB via código é feita **apenas para fins educacionais**, com o objetivo de aprender a integrar a AWS com o Spring Boot.  
   > Em ambientes reais, a abordagem mais indicada é criar esses recursos com uma ferramenta de **Infrastructure as Code**, como **Terraform**, e apenas conectar-se a eles via aplicação.

   EN:

   > **Note:** The creation of S3 buckets and DynamoDB tables via code is done **for educational purposes only**, with the goal of learning how to integrate AWS with Spring Boot.
   > In real-world environments, the recommended approach is to create these resources using an **Infrastructure as Code** tool, such as **Terraform**, and have the application simply connect to them.

---

### 🖥️ Frontend (React + Vite)

1. Instale as dependências | Install the dependencies :

   ```bash
   cd socketFrontend
   npm install
   ```

2. Inicie o servidor de desenvolvimento:
   ```bash
   npm run dev
   ```

---

## 🔌 WebSockets

A aplicação utiliza **WebSockets** para atualizações em tempo real. Ao adicionar ou modificar um álbum, todos os usuários conectados recebem as mudanças instantaneamente, sem necessidade de recarregar a página.

---

## 📦 Terraform (em breve)

A criação de recursos como buckets S3 e tabelas DynamoDB será migrada para **Terraform** para garantir versionamento, reprodutibilidade e uma infraestrutura mais sólida e segura.
