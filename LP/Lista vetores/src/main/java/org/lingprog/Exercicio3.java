package org.lingprog;

import java.util.Arrays;
import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numeros = new int[10];
        int contador = 0;
        for(int i=0;i<numeros.length;i++){
            System.out.print("Digite um número: ");
            int num=sc.nextInt();

            if(num%2==0){
                numeros[contador]=num; 
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("Nenhum número par foi armazenado.");
            return;
        }

        int[] pares = Arrays.copyOf(numeros, contador);

        int soma = 0;
        int maior = pares[0];
        for (int i = 0; i < pares.length; i++) {
            soma += pares[i];
            if (pares[i] > maior) {
                maior = pares[i];
            }
        }
        double media = (double) soma / pares.length;


        System.out.println("\nQuantidade de valores armazenados: " + pares.length);
        System.out.println("Maior valor armazenado: " + maior);
        System.out.printf("Média: %.1f\n", media);

        sc.close();
    }
}
