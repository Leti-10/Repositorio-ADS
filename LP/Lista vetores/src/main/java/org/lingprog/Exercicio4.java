package org.lingprog;

import java.util.Scanner;

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numeros = new int[4];
        for(int i=0;i<numeros.length;i++){
            System.out.print("Digite um número: ");
            numeros[i] = sc.nextInt();
        }

        System.out.print("Números inversos: ");
        for (int i = numeros.length - 1; i >= 0; i--){
            System.out.print(numeros[i]+" ");
        }
        sc.close();
    }
}
