package com.zupacademy.luizpedro.microserviceproposta.model;

import com.zupacademy.luizpedro.microserviceproposta.dto.BloqueioRequest;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String ipClient;

    private String userAgent;

    @ManyToOne
    private Cartao cartao;

    private LocalDateTime instanteBloqueio = LocalDateTime.now();

    @Deprecated
    public Bloqueio(){}

    public Bloqueio(String ipClient, String userAgent, Cartao cartao) {
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }
}
