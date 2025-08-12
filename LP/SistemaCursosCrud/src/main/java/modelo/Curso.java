package modelo;

import java.util.ArrayList;
import java.util.List;

public class Curso {

    private int idCurso;
    private String nome;
    private int cargaHoraria;
    private int limiteAlunos;
    private int ativo;
    private List<Aluno> listaAluno;
    private int qtdAlunos;

    public Curso() {
        this.listaAluno = new ArrayList<>();
    }

    public Curso(String nome, int cargaHoraria, int limiteAlunos, int ativo, List<Aluno> listaAluno) {
        if (nome == null || nome.length() < 3) {
            throw new IllegalArgumentException("O nome deve ter no mínimo 3 caracteres");
        }
        if (cargaHoraria < 20) {
            throw new IllegalArgumentException("Carga horária mínima é de 20 horas.");
        }
        if (limiteAlunos < 1) {
            throw new IllegalArgumentException("O limite de alunos deve ser no mínimo 1.");
        }

        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.limiteAlunos = limiteAlunos;
        this.ativo = ativo;
        this.listaAluno = listaAluno != null ? listaAluno : new ArrayList<>();
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getLimiteAlunos() {
        return limiteAlunos;
    }

    public void setLimiteAlunos(int limiteAlunos) {
        this.limiteAlunos = limiteAlunos;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public List<Aluno> getListaAluno() {
        return listaAluno;
    }

    public void setListaAluno(List<Aluno> listaAluno) {
        this.listaAluno = listaAluno;
    }

    public List<Aluno> getAlunos() {
        return getListaAluno();
    }

    public int getQtdAlunos() {
        return qtdAlunos;
    }

    public void setQtdAlunos(int qtdAlunos) {
        this.qtdAlunos = qtdAlunos;
    }
}
