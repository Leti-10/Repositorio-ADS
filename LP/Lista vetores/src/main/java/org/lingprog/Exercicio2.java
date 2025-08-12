package org.lingprog;

import java.util.Scanner;

public class Exercicio2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] numeros = new int[10];
        for(int i=0;i<numeros.length;i++){
            System.out.print("Digite um número: ");
            numeros[i]=sc.nextInt();
        }

        System.out.println("Os números digitados maiores ou iguais a 20 foram: ");
        for(int i=0;i<numeros.length;i++){
            if(numeros[i]>=20){
                System.out.print(numeros[i]+" ");
            }
        }
        sc.close();


    }
}
