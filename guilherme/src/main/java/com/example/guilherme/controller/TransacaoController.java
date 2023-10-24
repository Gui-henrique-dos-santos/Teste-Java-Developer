package com.example.guilherme.controller;

import com.example.guilherme.model.Transacao;
import com.example.guilherme.service.TransacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
@Api(value = "Transacao Controller")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;
    @ApiOperation(value = "Processar uma transação")
    @PostMapping("/processar")
    @ResponseStatus(HttpStatus.CREATED)
    public void processarTransacao(@RequestBody Transacao transacao) {
        transacaoService.processarTransacao(transacao);
    }
}


