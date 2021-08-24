package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;
import com.zupacademy.luizpedro.microserviceproposta.model.Proposta;
import com.zupacademy.luizpedro.microserviceproposta.model.StatusResultado;
import com.zupacademy.luizpedro.microserviceproposta.repository.PropostaRepository;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

public class PropostaResponse {

    private String documento;

    private String email;

    private String nome;

    private String endereco;

    private BigDecimal salario;

    private StatusResultado statusResultado;


    public PropostaResponse(Proposta proposta) {
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.statusResultado = proposta.getStatusResultado();
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusResultado getStatusResultado() {
        return statusResultado;
    }

}
