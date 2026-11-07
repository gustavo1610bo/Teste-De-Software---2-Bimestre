package br.edu.exemplo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static final double LIMITE_PARA_DESCONTO = 500.00;
    private static final double PERCENTUAL_DESCONTO = 0.10;

    private final List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
    }

    public void remover(Produto produto) {
        produtos.remove(produto);
    }

    /** Soma dos preços, sem desconto. */
    private double calcularSubtotal() {
        double subtotal = 0;
        for (Produto produto : produtos) {
            subtotal += produto.getPreco();
        }
        return subtotal;
    }

    /** Total a pagar: aplica 10% de desconto se o subtotal for >= R$ 500,00. */
    public double getTotal() {
        double subtotal = calcularSubtotal();

        if (subtotal >= LIMITE_PARA_DESCONTO) {
            return subtotal - (subtotal * PERCENTUAL_DESCONTO);
        }
        return subtotal;
    }
}
