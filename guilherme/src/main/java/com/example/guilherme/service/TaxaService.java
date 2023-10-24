package com.example.guilherme.service;

import com.example.guilherme.model.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxaService {
    @Autowired
    private EmpresaService empresaService;

    public double calcularTaxa(Transacao transacao) {
        // Lógica para calcular a taxa com base no tipo de transação e empresa envolvida
        String tipoTransacao = transacao.getTipo();
        String cnpjDestino = transacao.getCnpjDestino();

        // Exemplo de lógica de taxa simples (ajuste conforme necessário)
        if ("depósito".equalsIgnoreCase(tipoTransacao)) {
            if (cnpjDestino != null && !cnpjDestino.isEmpty()) {
                // Taxa de depósito para empresa
                double taxa = 10.0; // Exemplo de taxa
                return taxa;
            } else {
                // Taxa de depósito para cliente
                double taxa = 5.0; // Exemplo de taxa
                return taxa;
            }
        } else if ("saque".equalsIgnoreCase(tipoTransacao)) {
            if (cnpjDestino != null && !cnpjDestino.isEmpty()) {
                // Taxa de saque para empresa
                double taxa = 15.0; // Exemplo de taxa
                return taxa;
            } else {
                // Taxa de saque para cliente
                double taxa = 7.0; // Exemplo de taxa
                return taxa;
            }
        } else {
            throw new RuntimeException("Tipo de transação inválido.");
        }
    }

    public double aplicarTaxa(Transacao transacao) {
        double taxa = calcularTaxa(transacao);
        transacao.setValor(transacao.getValor() - taxa);
        return taxa;
    }
}

