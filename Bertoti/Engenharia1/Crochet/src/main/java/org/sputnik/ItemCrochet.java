package org.sputnik;

public class ItemCrochet {
    private String nome;
    private double preco;
    private int quantidade;

    public ItemCrochet(String nome, double preco, int quantidade){
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void diminuirEstoque(int quantidade) {
        if (quantidade <= getQuantidade()) {
            this.quantidade -= quantidade;
        } else {
            System.out.println("Estoque insuficiente para a venda.");
        }
    }


}
