# Sistema de Cursos CRUD

Sistema de gerenciamento de cursos e alunos desenvolvido em Java com JavaFX.

## Funcionalidades

### Gestão de Cursos
- ✅ Cadastrar novos cursos
- ✅ Editar cursos existentes
- ✅ Excluir cursos
- ✅ Listar todos os cursos
- ✅ Visualizar quantidade de alunos por curso

### Gestão de Alunos
- ✅ Cadastrar novos alunos
- ✅ Editar alunos existentes
- ✅ Excluir alunos
- ✅ Listar todos os alunos
- ✅ Buscar alunos por nome, CPF ou curso
- ✅ Associar alunos a cursos

## Tecnologias Utilizadas

- **Java 17+**
- **JavaFX 23**
- **MySQL/MariaDB**
- **Maven**

## Estrutura do Projeto

```
SistemaCursosCrud/
├── src/main/java/
│   ├── DAO/                    # Camada de acesso a dados
│   │   ├── AlunoDAO.java
│   │   └── CursoDAO.java
│   ├── factory/                # Factory para conexão com banco
│   │   └── Conexao.java
│   ├── gui/                    # Controladores JavaFX
│   │   ├── MainController.java
│   │   ├── AlunosController.java
│   │   └── CursosController.java
│   ├── modelo/                 # Modelos de dados
│   │   ├── Aluno.java
│   │   └── Curso.java
│   └── org/lingprog/sistemacursoscrud/
│       └── SistemaCursosCrudApplication.java
├── src/main/resources/
│   ├── Css/
│   │   └── estilo.css          # Estilos CSS para JavaFX
│   ├── imagens/                # Imagens do sistema
│   └── org/lingprog/sistemacursoscrud/
│       ├── main-view.fxml      # Tela principal
│       ├── alunos-view.fxml    # Tela de alunos
│       └── cursos-view.fxml    # Tela de cursos
└── pom.xml
```

## Como Executar

### Pré-requisitos
1. Java 17 ou superior
2. Maven
3. MySQL/MariaDB configurado

### Configuração do Banco de Dados
1. Crie um banco de dados MySQL
2. Configure a conexão no arquivo `factory/Conexao.java`
3. Execute os scripts SQL para criar as tabelas

### Executando o Sistema
```bash
# Compilar o projeto
mvn clean compile

# Executar a aplicação
mvn javafx:run
```

Ou execute diretamente a classe `SistemaCursosCrudApplication`

## Interface do Sistema

### Tela Principal
- Botão "Alunos": Abre a tela de gestão de alunos
- Botão "Cursos": Abre a tela de gestão de cursos

### Tela de Alunos
- **Aba Cadastrar**: Formulário para adicionar/editar alunos
- **Aba Listagem**: Tabela com todos os alunos e funcionalidade de busca

### Tela de Cursos
- **Aba Cadastrar**: Formulário para adicionar/editar cursos
- **Aba Listagem**: Tabela com todos os cursos

## Validações Implementadas

### Alunos
- Nome mínimo de 3 caracteres
- CPF com 11 dígitos
- Email em formato válido
- Idade mínima de 16 anos
- CPF único no sistema
- Curso obrigatório

### Cursos
- Nome mínimo de 3 caracteres
- Carga horária mínima de 20 horas
- Limite de alunos mínimo de 1

## Estilo Visual

O sistema utiliza um tema kawaii (fofo) com:
- Cores em tons de rosa (#ff486a, #ffb1cc, #fff0f5)
- Bordas arredondadas
- Efeitos de sombra
- Ícones e emojis decorativos

## Contribuição

Para contribuir com o projeto:
1. Faça um fork do repositório
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT. 