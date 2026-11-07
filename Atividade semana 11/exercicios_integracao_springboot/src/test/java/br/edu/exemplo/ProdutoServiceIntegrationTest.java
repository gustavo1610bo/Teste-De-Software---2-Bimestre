package br.edu.exemplo;

import br.edu.exemplo.model.Produto;
import br.edu.exemplo.repository.ProdutoRepository;
import br.edu.exemplo.service.ProdutoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercício 1 — integração Service + Repository + H2 (sem mocks).
 */
@SpringBootTest
@Transactional
class ProdutoServiceIntegrationTest {

    @Autowired
    ProdutoService service;

    @Autowired
    ProdutoRepository repository;

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Envia as alterações pendentes ao banco (flush) e esvazia o cache da
     * sessão (clear). Assim, o próximo findById() é obrigado a consultar o
     * H2 de verdade, em vez de devolver o mesmo objeto que já está em memória.
     */
    private void forcarIdaAoBanco() {
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @DisplayName("CT01 — Cadastro válido")
    void deveCadastrarERecuperarProduto() {
        Produto salvo = service.cadastrar("Teclado", 150.0);

        assertNotNull(salvo.getId(), "O produto cadastrado deve receber um ID");

        forcarIdaAoBanco();

        Produto encontrado = repository.findById(salvo.getId()).orElseThrow();
        assertEquals("Teclado", encontrado.getNome());
        assertEquals(150.0, encontrado.getPreco(), 0.001);
    }

    @Test
    @DisplayName("CT02 — Nome inválido")
    void naoDevePersistirProdutoComNomeEmBranco() {
        long antes = repository.count();

        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrar("   ", 80.0));

        assertEquals(antes, repository.count(),
                "Nenhum registro deve ser criado quando o nome é inválido");
    }

    @Test
    @DisplayName("CT03 — Atualização de preço (desafio)")
    void deveAtualizarPrecoPersistido() {
        Produto salvo = service.cadastrar("Mouse", 80.0);

        service.atualizarPreco(salvo.getId(), 95.0);

        forcarIdaAoBanco();

        Produto atualizado = repository.findById(salvo.getId()).orElseThrow();
        assertEquals(95.0, atualizado.getPreco(), 0.001);
    }
}
