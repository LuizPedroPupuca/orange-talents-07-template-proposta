package com.zupacademy.luizpedro.microserviceproposta.dto;

public class CarteiraDigitalApiResponse {

    private String resultado;

    private String id;

    @Deprecated
    public CarteiraDigitalApiResponse(){}

    public CarteiraDigitalApiResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
