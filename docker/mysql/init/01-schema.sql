CREATE DATABASE IF NOT EXISTS farmacia;
USE farmacia;

CREATE TABLE IF NOT EXISTS medicamentos (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(25),
  quantidade INT,
  tipo VARCHAR(25),
  valor FLOAT,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS autenticar (
  id INT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(25),
  password VARCHAR(25),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cliente (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(25),
  sobrenome VARCHAR(25),
  usuario VARCHAR(25),
  telefone VARCHAR(30),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS funcionarios (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(25),
  sobrenome VARCHAR(25),
  usuario VARCHAR(25),
  cargo VARCHAR(25),
  cpf VARCHAR(25),
  salario FLOAT,
  senha VARCHAR(25),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS registros (
  id INT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(25),
  medicamento VARCHAR(25),
  quantidade INT,
  valor FLOAT,
  data VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS carrinho (
  id INT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(25),
  medicamento VARCHAR(25),
  quantidade INT,
  valor FLOAT,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS encomendas (
  id INT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(25),
  medicamento VARCHAR(25),
  quantidade INT,
  valor INT,
  data VARCHAR(50),
  telefone VARCHAR(50),
  status VARCHAR(50),
  PRIMARY KEY (id)
);

INSERT INTO autenticar (usuario, password)
SELECT 'admin', 'admin123'
WHERE NOT EXISTS (
  SELECT 1 FROM autenticar WHERE usuario = 'admin'
);
