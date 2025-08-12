package org.lingprog;



public class Exercicio8 {
    public  static void main(String[] args) {
        int[] N = {12, 13, 14, 15, 17, 18, 19, 20, 21, 22};
        int[] M = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int p = 0;

        for (int j=0; j<N.length; j++){
            p += N[j]*M[j];
        }

        System.out.print("O produto escalar de N por M é: " + p);

    }
}
