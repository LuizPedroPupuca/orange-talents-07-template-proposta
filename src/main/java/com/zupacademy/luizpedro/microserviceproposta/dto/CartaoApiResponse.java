package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CartaoApiResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    private String idProposta;


    public Cartao toModel() {
        return new Cartao(id, emitidoEm, titular, limite);
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }


    public BigDecimal getLimite() {
        return limite;
    }


    public String getIdProposta() {
        return idProposta;
    }

}
