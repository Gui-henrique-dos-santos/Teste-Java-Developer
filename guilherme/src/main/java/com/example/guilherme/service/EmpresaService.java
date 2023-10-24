package com.example.guilherme.service;

import com.example.guilherme.model.Empresa;
import com.example.guilherme.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa buscarEmpresaPorCnpj(String cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj);
        if (empresa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada.");
        }
        return empresa;
    }

    public Empresa criarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public void excluirEmpresa(String cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj);
        if (empresa != null) {
            empresaRepository.delete(empresa);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada.");
        }
    }

    public void realizarDeposito(Empresa empresa, double valor) {
        if (valor <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O valor do depósito deve ser maior que zero.");
        }

        empresa.setSaldo(empresa.getSaldo() + valor);
        empresaRepository.save(empresa);
    }

    public void realizarSaque(Empresa empresa, double valor) {
        if (valor <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O valor do saque deve ser maior que zero.");
        }

        if (empresa.getSaldo() >= valor) {
            empresa.setSaldo(empresa.getSaldo() - valor);
            empresaRepository.save(empresa);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo insuficiente para realizar o saque.");
        }
    }
}
