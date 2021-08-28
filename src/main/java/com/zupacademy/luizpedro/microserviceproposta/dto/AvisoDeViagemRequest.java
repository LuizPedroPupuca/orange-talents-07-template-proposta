package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.model.AvisoDeViagem;
import com.zupacademy.luizpedro.microserviceproposta.model.Cartao;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoDeViagemRequest {

    @NotNull
    private Cartao cartao;

    @NotBlank
    private String destino;

    @NotNull @Future
    private LocalDate termino;

    @NotBlank
    private String ipClient;

    @NotBlank
    private String userAgent;

    public AvisoDeViagemRequest(Cartao cartao, String destino, LocalDate termino, String ipClient, String userAgent) {
        this.cartao = cartao;
        this.destino = destino;
        this.termino = termino;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
    }

    public AvisoDeViagem toModel(Cartao cartao, String ipClient, String userAgent){
        return new AvisoDeViagem(cartao, destino, termino, ipClient, userAgent);
    }

}
