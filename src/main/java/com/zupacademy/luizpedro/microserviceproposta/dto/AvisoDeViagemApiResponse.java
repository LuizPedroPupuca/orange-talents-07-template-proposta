package com.zupacademy.luizpedro.microserviceproposta.dto;

public class AvisoDeViagemApiResponse {

    private String resultado;

    @Deprecated
    public AvisoDeViagemApiResponse(){}

    public AvisoDeViagemApiResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
