package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;
import com.zupacademy.luizpedro.microserviceproposta.model.CarteiraDigital;
import com.zupacademy.luizpedro.microserviceproposta.model.Emissor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @NotBlank
    private String email;

    @NotNull
    private Emissor emissor;

    @Deprecated
    public CarteiraDigitalRequest(){}

    public CarteiraDigitalRequest(String email, Emissor emissor) {
        this.email = email;
        this.emissor = emissor;
    }

    public String getEmail() {
        return email;
    }

    public Emissor getEmissor() {
        return emissor;
    }

//    public Emissor stringToEnum() {
//        if (emissor.equals("Paypal"))
//            return Emissor.Paypal;
//        else if (emissor.equals("Samsung_Pay"))
//            return Emissor.Samsung_Pay;
//        return null;
//    }

    public CarteiraDigital toModel(Cartao cartao, String numeroCartao) {
        return new CarteiraDigital(email, cartao, emissor, numeroCartao);
    }
}
