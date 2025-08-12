package org.sputnik;

public class Peixe extends Animal {

    private  String tipoHabitat;

    public  Peixe(String nome, double peso, String tipoHabitat){
        super(nome, peso);
        this.tipoHabitat=tipoHabitat;
    }

    public String getTipoHabitat() {
        return tipoHabitat;
    }

    public void setTipoHabitat(String tipoHabitat) {
        this.tipoHabitat = tipoHabitat;
    }

    public String toString() {
        return "Nome: " + nome + "\tPeso: " + peso + "\tHabitat: " + tipoHabitat;
    }

}
