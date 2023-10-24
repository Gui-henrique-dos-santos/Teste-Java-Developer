package com.example.guilherme.repository;

import com.example.guilherme.model.Empresa;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EmpresaRepository extends CrudRepository<Empresa, String> {
    Empresa findByCnpj(String cnpj);

    List<Empresa> findAll();
}
