package org.sputnik;

public class Cachorro extends Animal {

    private String raça;

    public Cachorro(String nome, double peso, String raça) {
        super(nome, peso);
        this.raça=raça;
    }

    public String getRaça() {
        return raça;
    }

    public void setRaça(String raça) {
        this.raça=raça;
    }

    public String toString() {
        return "Nome: " + nome + "\tPeso: " + peso + "\tRaça: " + raça;
    }

}
