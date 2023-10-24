package com.example.guilherme.controller;


import com.example.guilherme.model.Empresa;
import com.example.guilherme.service.EmpresaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@Api(value = "Empresa Controller")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;
    @ApiOperation(value = "Criar uma nova empresa")
    @PostMapping("/criar")
    public Empresa criarEmpresa(@RequestBody Empresa empresa) {
        return empresaService.criarEmpresa(empresa);
    }
    @ApiOperation(value = "Listar todas as empresas")
    @GetMapping("/listar")
    public List<Empresa> listarEmpresas() {
        return empresaService.listarEmpresas();
    }

    @ApiOperation(value = "Excluir uma empresa por CNPJ")
    @DeleteMapping("/excluir/{cnpj}")
    public void excluirEmpresa(@PathVariable String cnpj) {
        empresaService.excluirEmpresa(cnpj);
    }
}

