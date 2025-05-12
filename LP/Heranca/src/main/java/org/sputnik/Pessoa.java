package org.sputnik;

public class Pessoa {

    protected String rg;
    protected String nome;

    public Pessoa(String rg, String nome){
        this.rg = rg;
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}




