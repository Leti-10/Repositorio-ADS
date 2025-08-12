package org.sputnik;

public class Zoo {
    public static void main(String[] args) {

        Peixe Dory = new Peixe("Dory",600.0,"Recifes de coral");
        Peixe Angie = new Peixe("Angie", 420, "Oceano Atlântico");
        Cachorro Bucky = new Cachorro("Bucky",6500.0,"Pequinês");
        Cachorro Bolt = new Cachorro("Bolt", 12000.0, "Pastor Branco Suíço");

        System.out.println("🐶 " + Bucky);
        System.out.println("🐶 " + Bolt);
        System.out.println("🐠 " + Dory);
        System.out.println("🐠 " + Angie);
    }
}