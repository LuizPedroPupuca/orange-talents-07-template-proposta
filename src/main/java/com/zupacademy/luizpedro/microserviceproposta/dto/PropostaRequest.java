package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.controller.validation.Documento;
import com.zupacademy.luizpedro.microserviceproposta.controller.validation.UniqueValue;
import com.zupacademy.luizpedro.microserviceproposta.model.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank @Documento
    private String documento;

    @NotBlank @Email @UniqueValue(fieldName = "email", domainClass = Proposta.class)
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull @PositiveOrZero
    private BigDecimal salario;

    public PropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(){
        return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }
}
