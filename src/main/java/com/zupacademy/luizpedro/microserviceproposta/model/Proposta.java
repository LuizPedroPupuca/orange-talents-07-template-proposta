package com.zupacademy.luizpedro.microserviceproposta.model;

import com.zupacademy.luizpedro.microserviceproposta.dto.ResultadoAnalise;
import org.springframework.security.crypto.bcrypt.BCrypt;

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

    @OneToOne(cascade = {CascadeType.MERGE})
    private Cartao cartao;

    @Deprecated
    public Proposta(){}

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = BCrypt.hashpw(documento, BCrypt.gensalt());
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

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusResultado getStatusResultado() {
        return statusResultado;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void associaCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public void atualizaStatus(Status status) {
        this.statusResultado = status.getStatusResultado();
    }

}
