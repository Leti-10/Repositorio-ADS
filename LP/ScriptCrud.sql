	create database sistema_cursos;

	CREATE TABLE curso(
		idCurso INT AUTO_INCREMENT PRIMARY KEY,
		nome VARCHAR(100) NOT NULL,
		carga_horaria INT NOT NULL,
		limite_alunos INT NOT NULL,
		ativo INT DEFAULT 1
		);
		
	CREATE TABLE aluno (
		idAluno INT AUTO_INCREMENT PRIMARY KEY,
		nome VARCHAR(100) NOT NULL,
		cpf VARCHAR(14) NOT NULL UNIQUE,
		email VARCHAR(100) NOT NULL,
		data_nascimento DATE NOT NULL,
		ativo INT DEFAULT 1,
        telefone VARCHAR(20),
		id_Curso INT,
		FOREIGN KEY (id_Curso) REFERENCES curso(idCurso)
	);
    
    SELECT 
    a.idAluno,
    a.nome AS aluno_nome,
    a.cpf,
    a.email,
    a.data_nascimento,
    a.ativo AS aluno_ativo,
    
    c.idCurso,
    c.nome AS curso_nome,
    c.carga_horaria,
    c.limite_alunos,
    c.ativo AS curso_ativo

FROM aluno aaluno
LEFT JOIN curso c ON a.id_Curso = c.idCurso
WHERE a.ativo = 1;

SELECT * FROM curso;
SELECT * FROM aluno;




