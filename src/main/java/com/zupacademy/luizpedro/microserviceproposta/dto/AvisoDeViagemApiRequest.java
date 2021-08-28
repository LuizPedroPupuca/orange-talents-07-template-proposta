package com.zupacademy.luizpedro.microserviceproposta.dto;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoDeViagemApiRequest {

    @NotBlank
    private String destino;

    @NotNull @Future
    private LocalDate validoAte;

    public AvisoDeViagemApiRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public AvisoDeViagemApiRequest(AvisoDeViagemRequest avisoDeViagemRequest) {
        this.destino = avisoDeViagemRequest.getDestino();
        this.validoAte = avisoDeViagemRequest.getTermino();
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
