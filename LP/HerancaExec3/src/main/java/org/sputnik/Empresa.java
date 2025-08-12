package org.sputnik;

public class Empresa {
    public static void main(String[] args) {

        Gerente Odisseu = new Gerente("Odisseu", 123456789, 2400.0, 12345 );
        Odisseu.setSenha(12345);
        Gerente Penelope = new Gerente("Penelope", 123456789, 3200.0, 12345 );
        Penelope.setSenha(12345);

        System.out.println("Gerente:" + Odisseu);
        System.out.println("Gerente:" + Penelope);

        System.out.println("Senha de Odisseu: "+ Odisseu.getSenha());
        boolean acesso = Odisseu.autentica(123456);
        System.out.println("Senha de Penelope: "+ Penelope.getSenha());
        boolean acesso2 = Penelope.autentica(12345);

        System.out.println("Autenticação de Odisseu: " + (acesso ? "Acesso concedido" : "Acesso negado"));
        System.out.println("Autenticação de Penelope: " + (acesso2 ? "Acesso concedido" : "Acesso negado"));

    }
}