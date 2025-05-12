CREATE DATABASE att4;
USE att4;

CREATE TABLE campanha (
    ID_campanha INT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Data_inicio DATE,
    Status_campanha VARCHAR(50)
);

CREATE TABLE meta (
    ID_meta INT PRIMARY KEY,
    Progresso DECIMAL(5,2),
    Data_alvo DATE,
    ID_campanha INT,
    FOREIGN KEY (ID_campanha) REFERENCES campanha(ID_campanha)
);

CREATE TABLE indicadores (
    ID_indicadores INT PRIMARY KEY,
    Nome VARCHAR(100),
    Valor DECIMAL(10,2),
    Periodo VARCHAR(50),
    ID_meta INT,
    FOREIGN KEY (ID_meta) REFERENCES meta(ID_meta)
);

CREATE TABLE evento (
    ID_evento INT PRIMARY KEY,
    date_evento DATE,
    Hora TIME,
    Local_evento VARCHAR(100),
    Vagas INT,
    ID_campanha INT,
    FOREIGN KEY (ID_campanha) REFERENCES campanha(ID_campanha)
);

CREATE TABLE usuario (
    ID_usuario INT PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Senha VARCHAR(100) NOT NULL
);

CREATE TABLE inscricao (
    ID_inscricao INT PRIMARY KEY,
    ID_usuario INT,
    ID_evento INT,
    Data_participacao DATE,
    FOREIGN KEY (ID_usuario) REFERENCES usuario(ID_usuario),
    FOREIGN KEY (ID_evento) REFERENCES evento(ID_evento)
);


INSERT INTO campanha (ID_campanha, Nome, Data_inicio, Status_campanha) VALUES
 (1, 'Campanha Verde', '2025-06-01', 'Ativa');

INSERT INTO meta (ID_meta, Progresso, Data_alvo, ID_campanha)
VALUES (1, 35.5, '2025-12-31', 1);

INSERT INTO indicadores (ID_indicadores, Nome, Valor, Periodo, ID_meta)
VALUES (1, 'Redução de CO2', 12.75, 'Mensal', 1);

INSERT INTO evento (ID_evento, date_evento, Hora, Local_evento, Vagas, ID_campanha) VALUES 
(1, '2025-07-10', '14:00:00', 'Parque Central', 100, 1),
(2, '2025-07-22', '18:00:00', 'Restaurante y', 250, 1);

INSERT INTO usuario (ID_usuario, Nome, Email, Senha) VALUES 
(1, 'Leticia Lopes', 'leticia@gmail.com', 'senha123'),
(2, 'Vinícius Lopes', 'vini@gmail.com', 'senha123'),
(3, 'Julia Soares', 'julia@gmail.com', 'senha123');

INSERT INTO inscricao (ID_inscricao, ID_usuario, ID_evento, Data_participacao)
VALUES (1, 1, 1, '2025-07-10');


-- select

SELECT * FROM campanha;

SELECT * FROM meta
WHERE Progresso > 30;

SELECT * FROM evento;

SELECT * FROM usuario;

SELECT * FROM indicadores;


-- update

UPDATE campanha
SET Status_campanha = 'Concluída'
WHERE ID_campanha = 1;

UPDATE meta
SET Progresso = 45.00
WHERE ID_meta = 1;

UPDATE evento
SET Local = 'Auditório Municipal'
WHERE ID_evento = 1;

-- delete

DELETE FROM inscricao
WHERE ID_inscricao IN (1, 2 ,3);

DELETE FROM usuario
WHERE ID_usuario IN (1, 2 ,3);

DELETE FROM evento
WHERE ID_evento IN (1, 2);

