package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.controller.validation.Documento;
import com.zupacademy.luizpedro.microserviceproposta.model.Proposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoAnalise {

    @Documento
    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String idProposta;

    public SolicitacaoAnalise(Proposta proposta){
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }



    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }


}
