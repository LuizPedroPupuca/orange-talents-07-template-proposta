package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.model.Bloqueio;
import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BloqueioRequest {

   @NotBlank
   private String sistemaResponsavel;

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
