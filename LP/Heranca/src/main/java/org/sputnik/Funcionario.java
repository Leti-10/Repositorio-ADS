package org.sputnik;

public class Funcionario extends Pessoa{

    private double salario;

    public Funcionario(String rg, String nome, double salario){
        super(rg, nome);
        this.salario = salario;
    }


    public double getSalario(){
        return salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    public double aumentar_Salario(double percentual){
        salario = salario + (salario * percentual);
        return salario;
    }

    public String toString() {
        return "Nome: " + nome +
                "\tRG: " + rg +
                "\tSalário: R$ " + String.format("%.2f", salario);
    }


}
