package com.zupacademy.luizpedro.microserviceproposta.dto;

public class CarteiraDigitalApiRequest {
    private String email;
    private String carteira;

    @Deprecated
    public CarteiraDigitalApiRequest(){}

    public CarteiraDigitalApiRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public CarteiraDigitalApiRequest(CarteiraDigitalRequest carteiraDigitalRequest) {
        this.email = carteiraDigitalRequest.getEmail();
        this.carteira = carteiraDigitalRequest.getEmissor();
    }

}
