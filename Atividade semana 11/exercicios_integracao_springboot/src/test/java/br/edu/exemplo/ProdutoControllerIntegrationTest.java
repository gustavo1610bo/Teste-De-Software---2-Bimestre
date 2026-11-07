package br.edu.exemplo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercício 2 — integração HTTP (Controller → Service → Repository → H2)
 * usando MockMvc, sem servidor HTTP real e sem mocks.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProdutoControllerIntegrationTest {

    private static final String JSON_MOUSE = "{\"nome\":\"Mouse\",\"preco\":80.0}";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("CT04 — Cadastro via API")
    void deveCadastrarProdutoPelaApi() throws Exception {
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_MOUSE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("Mouse"))
                .andExpect(jsonPath("$.preco").value(80.0));
    }

    @Test
    @DisplayName("CT05 — Consulta via API")
    void deveBuscarProdutoPelaApi() throws Exception {
        // 1) cadastra e captura o corpo da resposta
        String resposta = mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_MOUSE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // 2) extrai o ID gerado
        JsonNode corpo = objectMapper.readTree(resposta);
        long id = corpo.get("id").asLong();

        // 3) consulta pelo ID e confere os dados
        mockMvc.perform(get("/produtos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Mouse"))
                .andExpect(jsonPath("$.preco").value(80.0));
    }
}
