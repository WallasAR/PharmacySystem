USE farmacia;

INSERT INTO autenticar (usuario, password, role)
VALUES
  ('admin', 'admin123', 'ADMIN'),
  ('gerente', 'gerente123', 'ADMIN'),
  ('atendente1', 'atendente123', 'STAFF')
ON DUPLICATE KEY UPDATE
  password = VALUES(password),
  role = VALUES(role);

INSERT INTO funcionarios (nome, sobrenome, usuario, cargo, cpf, salario, senha)
VALUES
  ('Carlos', 'Silva', 'gerente', 'Gerente', '111.111.111-11', 6500.00, 'gerente123'),
  ('Ana', 'Souza', 'atendente1', 'Atendente', '222.222.222-22', 3200.00, 'atendente123')
ON DUPLICATE KEY UPDATE
  nome = VALUES(nome),
  sobrenome = VALUES(sobrenome),
  cargo = VALUES(cargo),
  salario = VALUES(salario),
  senha = VALUES(senha);

INSERT INTO cliente (nome, sobrenome, usuario, telefone)
VALUES
  ('Joao', 'Pereira', 'joao.pereira', '(11) 99999-1001'),
  ('Maria', 'Oliveira', 'maria.oliveira', '(11) 99999-1002'),
  ('Paula', 'Costa', 'paula.costa', '(11) 99999-1003')
ON DUPLICATE KEY UPDATE
  nome = VALUES(nome),
  sobrenome = VALUES(sobrenome),
  telefone = VALUES(telefone);

INSERT INTO medicamentos (nome, quantidade, tipo, valor)
VALUES
  ('Dipirona 500mg', 150, 'Analgesico', 12.90),
  ('Paracetamol 750mg', 200, 'Analgesico', 9.50),
  ('Amoxicilina 500mg', 90, 'Antibiotico', 28.00),
  ('Omeprazol 20mg', 180, 'Gastrico', 21.30),
  ('Loratadina 10mg', 120, 'Antialergico', 16.40)
ON DUPLICATE KEY UPDATE
  quantidade = VALUES(quantidade),
  tipo = VALUES(tipo),
  valor = VALUES(valor);

INSERT INTO carrinho (usuario, medicamento, quantidade, valor)
VALUES
  ('joao.pereira', 'Dipirona 500mg', 2, 25.80),
  ('maria.oliveira', 'Loratadina 10mg', 1, 16.40);

INSERT INTO registros (usuario, medicamento, quantidade, valor, data)
VALUES
  ('joao.pereira', 'Paracetamol 750mg', 1, 9.50, '2026-03-15 14:20:00'),
  ('paula.costa', 'Omeprazol 20mg', 2, 42.60, '2026-03-16 09:10:00');

INSERT INTO encomendas (usuario, medicamento, quantidade, valor, data, telefone, status)
VALUES
  ('maria.oliveira', 'Amoxicilina 500mg', 1, 28.00, '2026-03-17 16:30:00', '(11) 99999-1002', 'EM_SEPARACAO'),
  ('joao.pereira', 'Dipirona 500mg', 3, 38.70, '2026-03-18 11:00:00', '(11) 99999-1001', 'ENTREGUE');
