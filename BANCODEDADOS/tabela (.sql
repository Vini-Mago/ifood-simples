CREATE TABLE categoria (
  id_categoria BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  descricao VARCHAR(255) DEFAULT NULL,
  tipo ENUM('COMIDA', 'BEBIDA', 'ESTABELECIMENTO') NOT NULL,
  status ENUM('ATIVO', 'INATIVO', 'EXCLUÍDO') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_categoria)
);

CREATE TABLE endereco (
  id_endereco BIGINT NOT NULL AUTO_INCREMENT,
  tipo ENUM('RESIDENCIAL', 'COMERCIAL', 'OUTRO') NOT NULL,
  logradouro VARCHAR(255) NOT NULL,
  numero VARCHAR(20) NOT NULL,
  bairro VARCHAR(100) NOT NULL,
  cidade VARCHAR(100) NOT NULL,
  estado VARCHAR(2) NOT NULL,
  cep VARCHAR(10) NOT NULL,
  status ENUM('ATIVO', 'INATIVO', 'EXCLUÍDO') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_endereco)
);

CREATE TABLE metodo_pagamento (
  id_metodo_pagamento BIGINT NOT NULL AUTO_INCREMENT,
  tipo VARCHAR(255) NOT NULL,
  status ENUM('ATIVO', 'INATIVO', 'EXCLUÍDO') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_metodo_pagamento)
);


CREATE TABLE estabelecimento (
  id_estabelecimento BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  telefone VARCHAR(20) DEFAULT NULL,
  opcao_retirada BOOLEAN DEFAULT NULL,
  id_categoria BIGINT NOT NULL,
  id_endereco BIGINT NOT NULL,
  status ENUM('ATIVO', 'INATIVO', 'EXCLUÍDO') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_estabelecimento),
  FOREIGN KEY (id_categoria) REFERENCES categoria (id_categoria),
  FOREIGN KEY (id_endereco) REFERENCES endereco (id_endereco)
);

 
CREATE TABLE produto (
  id_produto BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  descricao VARCHAR(255) DEFAULT NULL,
  preco DECIMAL(10,2) NOT NULL,
  id_estabelecimento BIGINT NOT NULL,
  status ENUM('ATIVO', 'INATIVO', 'EXCLUÍDO') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_produto),
  FOREIGN KEY (id_estabelecimento) REFERENCES estabelecimento (id_estabelecimento)
);

CREATE TABLE item_adicional (
  id_item BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255) NOT NULL,
  descricao VARCHAR(255) DEFAULT NULL,
  preco DECIMAL(10,2) NOT NULL,
  id_estabelecimento BIGINT NOT NULL,
  status ENUM('ATIVO', 'INATIVO', 'EXCLUÍDO') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_item),
  FOREIGN KEY (id_estabelecimento) REFERENCES estabelecimento (id_estabelecimento)
);

CREATE TABLE pedido (
  id_pedido BIGINT NOT NULL AUTO_INCREMENT,
  valor_total DECIMAL(10,2) NOT NULL,
  taxa_entrega DECIMAL(10,2) DEFAULT NULL,
  id_endereco BIGINT DEFAULT NULL,
  observacao VARCHAR(255) DEFAULT NULL,
  status_pedido ENUM('PENDENTE', 'FINALIZADO', 'CANCELADO') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_pedido),
  FOREIGN KEY (id_endereco) REFERENCES endereco (id_endereco)
);

CREATE TABLE pedido_item (
  id_item_pedido BIGINT NOT NULL AUTO_INCREMENT,
  id_pedido BIGINT NOT NULL,
  id_produto BIGINT NOT NULL,
  quantidade INT NOT NULL,
  id_item_adicional BIGINT DEFAULT NULL,
  preco_unitario DECIMAL(10,2) NOT NULL,
  preco_total DECIMAL(10,2) NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_item_pedido),
  FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido),
  FOREIGN KEY (id_produto) REFERENCES produto (id_produto),
  FOREIGN KEY (id_item_adicional) REFERENCES item_adicional (id_item)
);


CREATE TABLE pagamento (
  id_pagamento BIGINT NOT NULL AUTO_INCREMENT,
  id_pedido BIGINT NOT NULL,
  id_metodo_pagamento BIGINT NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  data_pagamento DATETIME NOT NULL,
  status_pagamento ENUM('PENDENTE', 'CONFIRMADO', 'FALHOU') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_pagamento),
  FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido),
  FOREIGN KEY (id_metodo_pagamento) REFERENCES metodo_pagamento (id_metodo_pagamento)
);


CREATE TABLE historico_entrega (
  id_historico BIGINT NOT NULL AUTO_INCREMENT,
  id_pedido BIGINT NOT NULL,
  status_entrega ENUM('PENDENTE', 'EM_TRANSITO', 'ENTREGUE', 'CANCELADO') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_historico),
  FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido)
);


CREATE TABLE avaliacao (
  id_avaliacao BIGINT NOT NULL AUTO_INCREMENT,
  id_pedido BIGINT NOT NULL,
  id_estabelecimento BIGINT NOT NULL,
  nota INT NOT NULL,
  comentario VARCHAR(255) DEFAULT NULL,
  status ENUM('ATIVO', 'INATIVO', 'EXCLUÍDO') NOT NULL,
  criado_em DATETIME NOT NULL,
  atualizado_em DATETIME NOT NULL,
  PRIMARY KEY (id_avaliacao),
  FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido),
  FOREIGN KEY (id_estabelecimento) REFERENCES estabelecimento (id_estabelecimento)
);
