package com.example.guilherme.controller;

import com.example.guilherme.model.Cliente;
import com.example.guilherme.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    public void testCriarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");

        when(clienteService.criarCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Teste\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Teste")));
    }
    @Test
    public void testListarClientes() throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Cliente1");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Cliente2");

        when(clienteService.listarClientes()).thenReturn(List.of(cliente1, cliente2));

        mockMvc.perform(get("/clientes/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", is("Cliente1")))
                .andExpect(jsonPath("$[1].nome", is("Cliente2")));

        verify(clienteService, times(1)).listarClientes();

    }

    @Test
    public void testExcluirCliente() throws Exception {
        mockMvc.perform(delete("/clientes/excluir/12345"))
                .andExpect(status().isNoContent());

        verify(clienteService, times(1)).excluirCliente("12345");
    }
}
