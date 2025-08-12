package org.lingprog;

import java.util.Scanner;

public class Exercicio5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numeros = new int[8];
        for (int i = 0; i < numeros.length; i++){
            System.out.print("Digite um número: ");
            numeros[i] = sc.nextInt();
        }

        for(int i = 0; i < numeros.length/2; i++){
            int met= numeros[i];
            numeros[i] = numeros[i+numeros.length/2];
            numeros[i+numeros.length/2] = met;
        }

        System.out.println("Valores bagunçados: ");
        for (int i = 0; i < numeros.length; i++){
            System.out.print(numeros[i]+" ");
        }
        sc.close();
    }
}
