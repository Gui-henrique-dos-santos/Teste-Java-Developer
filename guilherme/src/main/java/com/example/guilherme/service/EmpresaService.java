package com.example.guilherme.service;

import com.example.guilherme.model.Empresa;
import com.example.guilherme.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa buscarEmpresaPorCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj);
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
            throw new RuntimeException("Empresa não encontrada.");
        }
    }

    public void realizarDeposito(Empresa empresa, double valor) {
        empresa.setSaldo(empresa.getSaldo() + valor);
        empresaRepository.save(empresa);
    }

    public void realizarSaque(Empresa empresa, double valor) {
        if (empresa.getSaldo() >= valor) {
            empresa.setSaldo(empresa.getSaldo() - valor);
            empresaRepository.save(empresa);
        } else {
            throw new RuntimeException("Saldo insuficiente para realizar o saque.");
        }
    }
}
