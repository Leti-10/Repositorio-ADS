package org.sputnik;

public class Main {
    public static void main(String[] args) {
        Funcionario f1 = new Funcionario("12.345.678-9","Alice", 2100.00);
        Funcionario f2 = new Funcionario("12.345.678-9", "Eduardo", 1300.00);
        Funcionario f3 = new Funcionario("12.345.678-9", "Jacob", 2550.00);

        System.out.println("Funcionário: " + f1);
        System.out.println("Funcionário: " + f2);
        System.out.println("Funcionário: " + f3);

        System.out.println("\n" +
                "Salários com aumento de 10%:\n");

        System.out.println("Alice: R$ " + f1.aumentar_Salario(0.10));
        System.out.println("Eduardo: R$ " + f2.aumentar_Salario(0.10));
        System.out.println("Jacob: R$ " + f3.aumentar_Salario(0.10));
    }
}