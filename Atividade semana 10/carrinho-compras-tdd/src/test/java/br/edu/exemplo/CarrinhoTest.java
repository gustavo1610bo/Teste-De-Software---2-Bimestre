package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarrinhoTest {

    // Requisito 1 — Carrinho vazio
    @Test
    void carrinhoNovoDeveTerTotalZero() {
        Carrinho carrinho = new Carrinho();

        assertEquals(0, carrinho.getTotal(), 0.001);
    }

    // Requisito 2 — Adicionar um produto
    @Test
    void deveAdicionarUmProduto() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Teclado", 100.00));

        assertEquals(100.00, carrinho.getTotal(), 0.001);
    }

    // Requisito 3 — Adicionar vários produtos
    @Test
    void deveSomarVariosProdutos() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Teclado", 100.00));
        carrinho.adicionar(new Produto("Mouse", 50.00));

        assertEquals(150.00, carrinho.getTotal(), 0.001);
    }

    // Requisito 4 — Remover produto
    @Test
    void deveRemoverProdutoDoCarrinho() {
        Carrinho carrinho = new Carrinho();
        Produto teclado = new Produto("Teclado", 100.00);
        Produto mouse = new Produto("Mouse", 50.00);
        carrinho.adicionar(teclado);
        carrinho.adicionar(mouse);

        carrinho.remover(mouse);

        assertEquals(100.00, carrinho.getTotal(), 0.001);
    }

    // Requisito 5 — Produto com preço inválido
    @Test
    void naoDevePermitirProdutoComPrecoNegativo() {
        Carrinho carrinho = new Carrinho();

        assertThrows(IllegalArgumentException.class,
                () -> carrinho.adicionar(new Produto("Teclado", -100.00)));

        assertEquals(0, carrinho.getTotal(), 0.001);
    }

    // Requisito 6 — Desconto de 10% para compras >= R$ 500,00
    @Test
    void deveAplicarDescontoDe10PorCentoEmCompraAcimaDoLimite() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Monitor", 600.00));

        assertEquals(540.00, carrinho.getTotal(), 0.001);
    }

    @Test
    void naoDeveAplicarDescontoEmCompraAbaixoDoLimite() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Headset", 499.99));

        assertEquals(499.99, carrinho.getTotal(), 0.001);
    }

    @Test
    void deveAplicarDescontoQuandoTotalForExatamenteOLimite() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Cadeira", 500.00));

        assertEquals(450.00, carrinho.getTotal(), 0.001);
    }

    @Test
    void deveAplicarDescontoQuandoTotalForLogoAcimaDoLimite() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Cadeira", 500.01));

        assertEquals(450.009, carrinho.getTotal(), 0.001);
    }

    @Test
    void deveAplicarDescontoSobreASomaDeVariosProdutos() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Teclado", 300.00));
        carrinho.adicionar(new Produto("Mouse", 300.00));

        assertEquals(540.00, carrinho.getTotal(), 0.001);
    }
}
