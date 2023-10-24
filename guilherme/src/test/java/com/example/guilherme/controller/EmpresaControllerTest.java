package com.example.guilherme.controller;

import com.example.guilherme.model.Empresa;
import com.example.guilherme.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

public class EmpresaControllerTest {

    @InjectMocks
    private EmpresaController empresaController;

    @Mock
    private EmpresaService empresaService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(empresaController).build();
    }

    @Test
    public void testCriarEmpresa() throws Exception {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("TesteEmpresa");

        when(empresaService.criarEmpresa(any(Empresa.class))).thenReturn(empresa);

        mockMvc.perform(post("/empresas/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"razaoSocial\":\"TesteEmpresa\"}")) // Correção aqui
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.razaoSocial", is("TesteEmpresa"))); // Correção aqui
    }


    @Test
    public void testListarEmpresas() throws Exception {
        Empresa empresa1 = new Empresa();
        empresa1.setRazaoSocial("Empresa1");

        Empresa empresa2 = new Empresa();
        empresa2.setRazaoSocial("Empresa2");

        when(empresaService.listarEmpresas()).thenReturn(List.of(empresa1, empresa2));

        mockMvc.perform(get("/empresas/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].razaoSocial", is("Empresa1")))
                .andExpect(jsonPath("$[1].razaoSocial", is("Empresa2")));

        verify(empresaService, times(1)).listarEmpresas();
    }

    @Test
    public void testExcluirEmpresa() throws Exception {
        mockMvc.perform(delete("/empresas/excluir/12345"))
                .andExpect(status().isOk());

        verify(empresaService, times(1)).excluirEmpresa("12345");
    }
}
