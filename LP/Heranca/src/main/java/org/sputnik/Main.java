package org.sputnik;

public class Main {
    public static void main(String[] args) {
        Funcionario Alice = new Funcionario("123.456.798-11","Alice", 2100.00);
        Funcionario Eduardo = new Funcionario("123.456.789-11", "Eduardo", 1300.00);
        Funcionario Jacob = new Funcionario("123.456.789-11", "Jacob", 2550.00);

        System.out.println("Salários atuais:\n");

        System.out.println("Alice: " + Alice.getSalario());
        System.out.println("Eduardo: " + Eduardo.getSalario());
        System.out.println("Jacob: " + Jacob.getSalario());

        System.out.println("\n" +
                "Salários com aumento de 10%:\n");

        System.out.println("Alice: R$ " + Alice.aumentar_Salario(0.10));
        System.out.println("Eduardo: R$ " + Eduardo.aumentar_Salario(0.10));
        System.out.println("Jacob: R$ " + Jacob.aumentar_Salario(0.10));
    }
}