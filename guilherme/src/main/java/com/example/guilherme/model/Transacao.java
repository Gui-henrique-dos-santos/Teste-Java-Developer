package com.example.guilherme.model;

import javax.persistence.*;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpfDestino;
    private String cnpjDestino;
    private double valor;
    private String tipo; // Pode ser "saque" ou "depósito"

    private String tipoDestino;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    public String getTipoDestino() {
        return tipoDestino;
    }

    public void setTipoDestino(String tipoDestino) {
        this.tipoDestino = tipoDestino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfDestino() {
        return cpfDestino;
    }

    public void setCpfDestino(String cpfDestino) {
        this.cpfDestino = cpfDestino;
    }

    public String getCnpjDestino() {
        return cnpjDestino;
    }

    public void setCnpjDestino(String cnpjDestino) {
        this.cnpjDestino = cnpjDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Outros getters e setters, se houverem
}
