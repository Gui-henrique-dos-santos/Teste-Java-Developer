package com.example.guilherme.repository;


import com.example.guilherme.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByCpfDestino(String cpfDestino);
    List<Transacao> findByCnpjDestino(String cnpjDestino);
    List<Transacao> findByTipo(String tipo);
    List<Transacao> findByCpfDestinoAndTipo(String cpfDestino, String tipo);
    List<Transacao> findByCnpjDestinoAndTipo(String cnpjDestino, String tipo);
    List<Transacao> findByCpfDestinoOrCnpjDestino(String cpfDestino, String cnpjDestino);
}

