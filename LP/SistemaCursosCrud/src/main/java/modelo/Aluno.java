package modelo;

import java.time.LocalDate;

public class Aluno {

    private int idAluno;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDate data_Nascimento;
    private int ativo;
    private Curso curso;

    public Aluno() {
    }

    public Aluno(String nome, String cpf, String telefone, String email, LocalDate dataNascimento, int ativo ,Curso curso) {
        if(nome == null || nome.length() <3){
            throw new IllegalArgumentException("O nome deve conter no mínimo 3 caracteres.");
        }
        if(cpf == null || !cpf.matches("\\d{11}")){
            throw new IllegalArgumentException("O cpf deve conter exatamente 11 digitos.");
        }
        if(email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            throw new IllegalArgumentException("Formato de E-mail inválido.");
        }
        if(dataNascimento == null|| calcularIdade(dataNascimento)<16){
            throw new IllegalArgumentException("O aluno deve ter a idade mínima de 16 anos.");
        }
        if(dataNascimento == null|| calcularIdade(dataNascimento)>100){
            throw new IllegalArgumentException("A data deve ser válida!");
        }
        if(curso == null){
            throw new IllegalArgumentException("O aluno deve estar cadastrado em algum curso.");
        }

        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.data_Nascimento = dataNascimento;
        this.ativo = ativo;
        this.curso = curso;

    }

    private int calcularIdade(LocalDate nascimento) {
        return LocalDate.now().getYear() - nascimento.getYear();
    }

    public int getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData_Nascimento() {
        return data_Nascimento;
    }
    public void setData_Nascimento(LocalDate data_Nascimento) {
        this.data_Nascimento = data_Nascimento;
    }

    public int getAtivo() {
        return ativo;
    }
    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }



}
