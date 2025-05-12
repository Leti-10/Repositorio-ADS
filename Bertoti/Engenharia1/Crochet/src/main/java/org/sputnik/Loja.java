package org.sputnik;

import java.util.LinkedList;
import java.util.List;

public class Loja {
    private List<ItemCrochet> itens = new LinkedList<ItemCrochet>();

    public void addItem(ItemCrochet item) {
        itens.add(item);
    }

    public ItemCrochet buscarItemPorNome(String nome) {
        for (ItemCrochet item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                return item;
            }
        }
        return null;
    }

    public void mostrarQuantidadePorNome(String nome) {
        ItemCrochet item = buscarItemPorNome(nome);
        if (item != null) {
            System.out.println("Item: " + item.getNome() +
                    " | Quantidade em estoque: " + item.getQuantidade());
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    public void venderItem(String nome, int quantidade) {
        for (ItemCrochet item : itens) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                if (item.getQuantidade() >= quantidade) {
                    item.diminuirEstoque(quantidade); // atualiza o estoque
                    double total = quantidade * item.getPreco();
                    System.out.println("Venda realizada!");
                    System.out.println("Item: " + item.getNome());
                    System.out.println("Quantidade vendida: " + quantidade);
                    System.out.println("Total: R$ " + total);
                    System.out.println("Restante no Estoque: " + item.getQuantidade());
                    return;
                } else {
                    System.out.println("Estoque insuficiente para " + nome + ".");
                    return;
                }
            }
        }
        System.out.println("Item não encontrado.");
    }

}



