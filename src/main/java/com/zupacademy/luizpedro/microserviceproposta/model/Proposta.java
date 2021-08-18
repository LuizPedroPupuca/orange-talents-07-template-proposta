package com.zupacademy.luizpedro.microserviceproposta.model;

import com.zupacademy.luizpedro.microserviceproposta.dto.ResultadoAnalise;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private StatusResultado statusResultado;

    @Deprecated
    public Proposta(){}

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.statusResultado = StatusResultado.NAO_ELEGIVEL;
    }



    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public void atualizaStatus(Status status) {
        this.statusResultado = status.getStatusResultado();
    }
}
