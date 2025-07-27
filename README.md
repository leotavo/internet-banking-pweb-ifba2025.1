# 🏦 Internet Banking App

Aplicação **fullstack** de Internet Banking, construída com **Spring Boot**, **React** e um **microserviço de envio de e-mails**.

O sistema permite:
- Cadastro de usuários e criação automática de conta corrente
- Operações bancárias: depósito, saque e pagamento
- Consulta de extrato com filtros
- Notificações por e-mail a cada transação realizada

---

## 🧱 Estrutura do Projeto

```
├── backend/
│   └── bank-api/               # API principal
├── email-service/             # Microserviço de envio de e-mail
├── frontend/
│   └── react-app/             # Interface web (SPA)
├── gateway/                   # API Gateway (roteamento centralizado)
├── eureka-server/             # Service Discovery com Eureka
├── docs/                      # Documentação do projeto
│   ├── Especificação do Sistema de Internet Banking.pdf
│   ├── Etapas_Backend_EmailService.pdf
│   ├── Microserviços com Spring Boot.pdf
│   ├── React JS.pdf
│   └── Spring Boot.pdf
├── README.md
└── .gitignore
```

---

## 🚀 Como Executar Localmente

### 🔧 Pré-requisitos

- Java 17+
- Node.js (versão LTS recomendada)
- PostgreSQL rodando localmente com os bancos:
  - `banco` (para o sistema principal)
  - `bancoemail` (para o serviço de e-mail)

### ▶️ Etapas

1. **Configure o banco de dados**
   - Crie os bancos `banco` e `bancoemail`
   - Atualize `application.properties` com usuário e senha corretos

2. **Inicie o microserviço de e-mail**
   ```bash
   cd email-service
   ./mvnw spring-boot:run
   ```

3. **Inicie a API principal**
   ```bash
   cd backend/bank-api
   ./mvnw spring-boot:run
   ```

4. **Inicie o frontend**
   ```bash
   cd frontend/react-app
   npm install
   npm run dev
   ```

---

## 📡 Comunicação entre Serviços

- A `bank-api` envia notificações via `POST http://localhost:8081/email/send`
- O frontend consome a API usando **Axios**, com dados em formato JSON

---

## 🔐 Segurança e Documentação

Este sistema será protegido com autenticação via **JWT (JSON Web Token)**, garantindo acesso apenas a usuários autenticados.

A documentação da API REST será gerada com **Swagger UI**, usando a biblioteca **Springdoc OpenAPI**.

- 🔒 Autenticação JWT em implementação  
- 📘 Swagger será acessível em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🧪 Exemplos de Uso

### ➕ Cadastro de usuário (POST `/usuarios`)
- Cria o usuário, a conta automaticamente e dispara e-mail de boas-vindas

### 💸 Operações bancárias (POST `/operacoes`)
- Tipos: `DEPOSITO`, `SAQUE`, `PAGAMENTO`
- Validação: valor > 0, saldo suficiente, descrição obrigatória (pagamento)

### 📄 Extrato (GET `/operacoes`)
- Permite filtros por tipo e intervalo de datas

---

## 📚 Documentação

| Documento | Descrição |
|-----------|-----------|
| [📄 Especificação do Sistema](docs/Especifica%C3%A7%C3%A3o%20do%20Sistema%20de%20Internet%20Banking.pdf) | Regras de negócio e requisitos funcionais |
| [📘 Apostila Spring Boot](docs/Spring%20Boot.pdf) | Fundamentos do backend com Spring |
| [📙 Apostila React](docs/React%20JS.pdf) | Fundamentos do frontend com React |
| [📗 Microserviços com Spring Boot](docs/Microservi%C3%A7os%20com%20%20Spring%20Boot.pdf) | Implementação do `email-service` |
| [📑 Etapas Backend/Email](docs/Etapas_Backend_EmailService.pdf) | Checklist de tarefas divididas |

---

## ✅ Status do Projeto

- [ ] Backend funcional com JPA e validações
- [ ] Microserviço de e-mail testado via Postman
- [ ] Frontend React integrado à API
- [ ] Segurança com JWT em desenvolvimento
- [ ] Swagger pendente de configuração
- [ ] Deploy final em produção (em aberto)

---

## 🤝 Autores

- Leonardo Trindade de Jesus – leotavo@gmail.com
- Marinaldo – ti.marinaldo@gmail.com

---

## 🏫 Instituição, Disciplina e Semestre

- **IFBA – Instituto Federal da Bahia – Campus Salvador**
- **Disciplina:** INF012 – Programação Web
- **Semestre:** 2025.1
- **Professor:** Manoel Neto

---

## 🛡️ Licença

Projeto acadêmico sem fins lucrativos. Todos os direitos reservados aos autores e à instituição de ensino.
