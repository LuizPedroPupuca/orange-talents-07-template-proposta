package com.zupacademy.luizpedro.microserviceproposta.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    private LocalDateTime emitidoEm;

    private String titular;

    private BigDecimal limite;

    @Enumerated(EnumType.STRING)
    private StatusCartao status;


    @Deprecated
    public Cartao(){}

    public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, BigDecimal limite) {
        this.numeroCartao = numeroCartao;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.status = StatusCartao.ativo;
    }

    public String getNumeroCartao() {
        return numeroCartao;
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

    public StatusCartao getStatus() {
        return status;
    }

    public void bloqueioCartao(){
        this.status = StatusCartao.bloqueado;
    }
}
