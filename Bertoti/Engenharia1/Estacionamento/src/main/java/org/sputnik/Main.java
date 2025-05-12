package org.sputnik;

public class Main {
    public static void main(String[] args) {
        Estacionamento est = new Estacionamento();

        est.addCarro(new Carro("gol", "ABC1234"));
        est.addCarro(new Carro("fox", "DEF5678"));

        System.out.println("Total de carros: " + est.getCarros().size());

        Carro c = est.buscarCarroPlaca("ABC1234");
        if (c != null) {
            System.out.println("Carro encontrado: " + c.getModelo());
        } else {
            System.out.println("Carro não encontrado.");
        }
    }
}

