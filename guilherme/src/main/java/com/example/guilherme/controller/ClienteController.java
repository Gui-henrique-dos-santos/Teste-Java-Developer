package com.example.guilherme.controller;

import com.example.guilherme.model.Cliente;
import com.example.guilherme.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Api(value = "Cliente Controller")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @ApiOperation(value = "Criar um novo cliente")
    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteService.criarCliente(cliente);
    }
    @ApiOperation(value = "Listar todos os clientes")
    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }
    @ApiOperation(value = "Excluir um cliente por CPF")
    @DeleteMapping("/excluir/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCliente(@PathVariable String cpf) {
        clienteService.excluirCliente(cpf);
    }
}


