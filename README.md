# Internet Banking App

Aplicação fullstack com Spring Boot, React e microserviço de e-mail.

## Estrutura
- `/backend/bank-api`: API principal (usuários, contas, operações)
- `/email-service`: Microserviço de envio de e-mail
- `/frontend/react-app`: Interface do usuário em React

## Como rodar
1. PostgreSQL rodando localmente
2. Iniciar o `email-service`
3. Iniciar o `bank-api`
4. Rodar `frontend/react-app` com `npm run dev`

## Observações
- A API se comunica com o serviço de e-mail via HTTP POST.
- O frontend usa Axios para consumir o backend.
