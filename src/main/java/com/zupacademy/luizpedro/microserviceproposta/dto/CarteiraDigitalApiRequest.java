package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.model.Emissor;

public class CarteiraDigitalApiRequest {
    private String email;
    private Emissor carteira;

    @Deprecated
    public CarteiraDigitalApiRequest(){}

    public CarteiraDigitalApiRequest(String email, Emissor carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public Emissor getCarteira() {
        return carteira;
    }

    public CarteiraDigitalApiRequest(CarteiraDigitalRequest carteiraDigitalRequest) {
        this.email = carteiraDigitalRequest.getEmail();
        this.carteira = carteiraDigitalRequest.getEmissor();
    }

}
