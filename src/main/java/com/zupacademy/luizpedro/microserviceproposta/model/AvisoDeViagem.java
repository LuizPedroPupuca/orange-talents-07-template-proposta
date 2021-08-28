package com.zupacademy.luizpedro.microserviceproposta.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoDeViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cartao cartao;

    private String destino;

    private LocalDate termino;

    private LocalDateTime instante = LocalDateTime.now();

    private String ipClient;

    private String userAgent;

    private StatusAviso status = StatusAviso.NAO_CRIADO;

    @Deprecated
    public AvisoDeViagem(){}

    public AvisoDeViagem(Cartao cartao, String destino, LocalDate termino, String ipClient, String userAgent) {
        this.destino = destino;
        this.termino = termino;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

}
