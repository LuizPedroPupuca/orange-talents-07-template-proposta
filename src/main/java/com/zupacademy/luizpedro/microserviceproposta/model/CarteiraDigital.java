package com.zupacademy.luizpedro.microserviceproposta.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CarteiraDigital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private LocalDateTime associadaEm = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    @Enumerated(EnumType.STRING)
    private Emissor emissor;

    private String numeroCartao;


    @Deprecated
    public CarteiraDigital(){}

    public CarteiraDigital(String email, Cartao cartao, Emissor emissor, String numeroCartao) {
        this.email = email;
        this.cartao = cartao;
        this.emissor = emissor;
        this.numeroCartao = numeroCartao;
    }

    public Long getId() {
        return id;
    }

    public Emissor getEmissor() {
        return emissor;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
