-- Criação do banco
CREATE DATABASE banco_db;

-- Conecte-se ao banco criado (ou use no pgAdmin)
\c banco_db;

-- Tabela: role
CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE
);

-- Tabela: usuario
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Tabela associativa: usuario_roles (N:N entre usuario e role)
CREATE TABLE usuario_roles (
    usuario_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (usuario_id, role_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

-- Tabela: conta
CREATE TABLE conta (
    numero BIGINT NOT NULL,
    agencia VARCHAR(10) NOT NULL,
    saldo NUMERIC(15, 2) NOT NULL DEFAULT 0.00,
    usuario_id INTEGER NOT NULL,
    PRIMARY KEY (numero, agencia),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- Tabela: operacao
CREATE TABLE operacao (
    id SERIAL PRIMARY KEY,
    tipo_operacao VARCHAR(50) NOT NULL,
    valor NUMERIC(15, 2) NOT NULL CHECK (valor >= 0),
    data_operacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    conta_numero BIGINT NOT NULL,
    conta_agencia VARCHAR(10) NOT NULL,
    FOREIGN KEY (conta_numero, conta_agencia) REFERENCES conta(numero, agencia) ON DELETE CASCADE
);
