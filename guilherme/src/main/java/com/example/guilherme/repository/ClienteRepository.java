package com.example.guilherme.repository;

import com.example.guilherme.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, String> {
    Cliente findByCpf(String cpf);

    List<Cliente> findAll();

}

