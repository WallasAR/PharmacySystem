CREATE DATABASE IF NOT EXISTS farmacia;
USE farmacia;

CREATE TABLE IF NOT EXISTS medicamentos (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(80) NOT NULL,
  quantidade INT NOT NULL DEFAULT 0,
  tipo VARCHAR(40) NOT NULL,
  valor DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uk_medicamentos_nome (nome),
  CONSTRAINT chk_medicamentos_quantidade CHECK (quantidade >= 0),
  CONSTRAINT chk_medicamentos_valor CHECK (valor >= 0)
);

CREATE TABLE IF NOT EXISTS autenticar (
  id INT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(20) NOT NULL DEFAULT 'ADMIN',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uk_autenticar_usuario (usuario)
);

CREATE TABLE IF NOT EXISTS cliente (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  sobrenome VARCHAR(80) NOT NULL,
  usuario VARCHAR(50) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uk_cliente_usuario (usuario)
);

CREATE TABLE IF NOT EXISTS funcionarios (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  sobrenome VARCHAR(80) NOT NULL,
  usuario VARCHAR(50) NOT NULL,
  cargo VARCHAR(40) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  salario DECIMAL(10,2) NOT NULL DEFAULT 0.00,
  senha VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY uk_funcionarios_usuario (usuario),
  UNIQUE KEY uk_funcionarios_cpf (cpf),
  CONSTRAINT chk_funcionarios_salario CHECK (salario >= 0)
);

CREATE TABLE IF NOT EXISTS registros (
  id INT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(50) NOT NULL,
  medicamento VARCHAR(80) NOT NULL,
  quantidade INT NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  data VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  INDEX idx_registros_usuario (usuario),
  INDEX idx_registros_medicamento (medicamento),
  CONSTRAINT chk_registros_quantidade CHECK (quantidade > 0),
  CONSTRAINT chk_registros_valor CHECK (valor >= 0)
);

CREATE TABLE IF NOT EXISTS carrinho (
  id INT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(50) NOT NULL,
  medicamento VARCHAR(80) NOT NULL,
  quantidade INT NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  INDEX idx_carrinho_usuario (usuario),
  INDEX idx_carrinho_medicamento (medicamento),
  CONSTRAINT chk_carrinho_quantidade CHECK (quantidade > 0),
  CONSTRAINT chk_carrinho_valor CHECK (valor >= 0)
);

CREATE TABLE IF NOT EXISTS encomendas (
  id INT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(50) NOT NULL,
  medicamento VARCHAR(80) NOT NULL,
  quantidade INT NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  data VARCHAR(50) NOT NULL,
  telefone VARCHAR(20) NOT NULL,
  status VARCHAR(30) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  INDEX idx_encomendas_usuario (usuario),
  INDEX idx_encomendas_status (status),
  CONSTRAINT chk_encomendas_quantidade CHECK (quantidade > 0),
  CONSTRAINT chk_encomendas_valor CHECK (valor >= 0)
);

INSERT INTO autenticar (usuario, password)
SELECT 'admin', 'admin123'
WHERE NOT EXISTS (
  SELECT 1 FROM autenticar WHERE usuario = 'admin'
);
