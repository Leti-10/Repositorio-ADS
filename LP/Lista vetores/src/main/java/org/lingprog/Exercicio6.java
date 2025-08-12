package org.lingprog;

import java.util.Scanner;

public class Exercicio6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numeros = {1,23,4,54,23,2,4,5,74,21};
        System.out.println("Digite um número para encontrar: ");
        int p=sc.nextInt();

        for (int i=0;i<numeros.length;i++){
            if(numeros[i]==p){
                System.out.println("Número encontrado na posição: "+i);
                break;
            }
            else if(i==numeros.length-1){
                System.out.print("Número não encontrado no vetor");
            }
        }
        sc.close();

    }
}
