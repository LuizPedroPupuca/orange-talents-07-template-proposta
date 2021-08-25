package com.zupacademy.luizpedro.microserviceproposta.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    private String dedo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    Biometria(){}

    public Biometria(DedoEmBase64 dedo, Cartao cartao) {
        this.dedo = dedo.getDedo();
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

}
