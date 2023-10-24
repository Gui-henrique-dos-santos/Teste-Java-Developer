package com.example.guilherme.service;

import com.example.guilherme.model.Cliente;
import com.example.guilherme.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String webhookUrl = "https://webhook.site/42a0c8a7-1000-49d5-a601-79401c6a4c57";

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public void excluirCliente(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        } else {
            throw new RuntimeException("Cliente não encontrado.");
        }
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
