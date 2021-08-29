package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;
import com.zupacademy.luizpedro.microserviceproposta.model.CarteiraDigital;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @NotBlank
    private String email;

    @NotNull
    private String emissor;

    @Deprecated
    public CarteiraDigitalRequest(){}

    public CarteiraDigitalRequest(String email, String emissor) {
        this.email = email;
        this.emissor = emissor;
    }

    public String getEmail() {
        return email;
    }

    public String getEmissor() {
        return emissor;
    }

    public CarteiraDigital toModel(Cartao cartao, String numeroCartao) {
        return new CarteiraDigital(email, cartao, emissor, numeroCartao);
    }
}
