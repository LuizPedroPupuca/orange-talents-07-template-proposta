package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.zupacademy.luizpedro.microserviceproposta.model.Biometria;
import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;
import com.zupacademy.luizpedro.microserviceproposta.model.DedoEmBase64;
import com.zupacademy.luizpedro.microserviceproposta.repository.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.Basic;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class BiometriaRequest {

    @NotBlank
    private String dedo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaRequest(String dedo) {
        this.dedo = dedo;
    }



    public Biometria toModel(Cartao cartao){
        return new Biometria(new DedoEmBase64(dedo), cartao);
    }

    public String getDedo() {
        return dedo;
    }
}
