package br.edu.exemplo;

public class Produto {

    private String nome;
    private int quantidade;

    public Produto(String nome) {
        this.nome = nome;
        this.quantidade = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        // Entrada negativa ou zero é inválida e não altera o estoque.
        if (quantidade > 0) {
            this.quantidade += quantidade;
        }
    }

    public void retirarEstoque(int quantidade) {
        // Retirada maior que o disponível é inválida e não altera o estoque.
        if (quantidade <= this.quantidade) {
            this.quantidade -= quantidade;
        }
    }
}
