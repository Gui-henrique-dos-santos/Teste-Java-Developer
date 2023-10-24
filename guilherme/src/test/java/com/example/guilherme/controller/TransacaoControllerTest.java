package com.example.guilherme.controller;

import com.example.guilherme.controller.TransacaoController;
import com.example.guilherme.model.Transacao;
import com.example.guilherme.service.TransacaoService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class TransacaoControllerTest {

    @Autowired
    private TransacaoController transacaoController;

    @MockBean
    private TransacaoService transacaoService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(transacaoController);
    }

    @Test
    public void testProcessarTransacao() {
        // Mock do serviço para processar transação
        Transacao transacao = new Transacao();
        transacao.setTipoDestino("empresa");

        // Suponha que você tenha configurado o serviço apropriadamente para este teste

        // Teste do processamento da transação
        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(transacao)
                .when()
                .post("/transacoes/processar")
                .then()
                .statusCode(200);
    }
}

