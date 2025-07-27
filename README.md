# 🏦 Internet Banking App

Aplicação **fullstack** de Internet Banking, construída com **Spring Boot**, **React** e um **microserviço de envio de e-mails**.

O sistema permite:
- Cadastro de usuários e criação automática de conta corrente
- Operações bancárias: depósito, saque, pagamento
- Consulta de extrato com filtros
- Notificações por e-mail em cada transação

---

## 🧱 Estrutura do Projeto

```
├── backend/
│   └── bank-api/               # (a ser criado)
├── email-service/             # (a ser criado)
├── frontend/
│   └── react-app/             # (a ser criado)
├── gateway/                   # API Gateway (recomendado)
├── eurekaserver/              # Service Discovery com Eureka
├── docs/
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
- Node.js (recomendado: versão LTS)
- PostgreSQL (rodando localmente com duas bases: `banco`, `bancoemail`)

### ▶️ Etapas

1. **Configure o banco de dados**
   - Crie os bancos `banco` e `bancoemail`
   - Atualize usuário/senha se necessário nos arquivos `application.properties`

2. **Rode o microserviço de e-mail**
   ```bash
   cd email-service
   ./mvnw spring-boot:run
   ```

3. **Rode a API principal**
   ```bash
   cd backend/bank-api
   ./mvnw spring-boot:run
   ```

4. **Rode o frontend**
   ```bash
   cd frontend/react-app
   npm install
   npm run dev
   ```

---

## 📡 Comunicação entre serviços

- A API principal chama o `email-service` via `POST http://localhost:8081/email/send`
- O frontend consome o backend usando **Axios**, enviando/recebendo dados no formato JSON.

---

## 🧪 Exemplos de Uso

### Cadastro de usuário (POST `/usuarios`)
- Cria o usuário, a conta automaticamente e dispara e-mail de boas-vindas

### Operações bancárias (POST `/operacoes`)
- Tipos: `DEPOSITO`, `SAQUE`, `PAGAMENTO`
- Regras de validação: valor > 0, saldo suficiente, descrição obrigatória no pagamento

### Extrato (GET `/operacoes`)
- Permite filtrar por tipo e intervalo de datas

---

## 📚 Documentação

| Documento | Descrição |
|-----------|-----------|
| [📄 Especificação do Sistema](docs/Especificação do Sistema de Internet Banking.pdf) | Regras de negócio e requisitos funcionais |
| [📘 Apostila Spring Boot](docs/Spring Boot.pdf) | Base para o desenvolvimento do backend |
| [📙 Apostila React](docs/React JS.pdf) | Base para desenvolvimento do frontend |
| [📗 Microserviços com Spring Boot](docs/Microserviços com  Spring Boot.pdf) | Implementação do serviço de e-mail |
| [📑 Etapas divididas para backend/email](docs/Etapas_Backend_EmailService.pdf) | Checklist com divisão por pessoa |

---

## ✅ Status do Projeto

- [ ] Backend funcional com JPA e validação
- [ ] Microserviço de e-mail testado via Postman
- [ ] Frontend React com páginas conectadas à API
- [ ] Integração com autenticação (em aberto)
- [ ] Deploy em produção (em aberto)

---

## 🤝 Autores

- Leonardo Trindade de Jesus - leotavo@gmail.com
- Marinaldo - ti.marinaldo@gmail.com

---

## 🏫 Instituição, Disciplina e Semestre

- **IFBA - Instituto Federal de Educação, Ciência e Tecnologia da Bahia - Campus Salvador**
- **Disciplina**: INF012 - Programação Web
- **Semestre**: 2025.1
- **Professor**: Manoel Neto

---

## 🛡️ Licença

Este projeto é acadêmico, sem fins lucrativos. Direitos reservados aos autores e instituição de ensino.
