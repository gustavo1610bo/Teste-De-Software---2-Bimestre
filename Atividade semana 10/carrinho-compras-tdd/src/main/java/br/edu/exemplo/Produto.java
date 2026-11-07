package br.edu.exemplo;

public class Produto {

    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo: " + preco);
        }
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
