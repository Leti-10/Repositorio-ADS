package org.lingprog;

import java.util.Scanner;

public class Exercicio7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] A = {1, 45, 6, 7, 3};
        int[] B = {2, 45, 6, 8, 9, 10, 3, 16};

        System.out.print("Números em comum: ");
        for(int i=0; i<A.length; i++){
            for(int j=0; j<B.length; j++){
                if(A[i]==B[j]){
                    System.out.print(A[i]+" ");
                }
            }

        }
        sc.close();
    }
}
