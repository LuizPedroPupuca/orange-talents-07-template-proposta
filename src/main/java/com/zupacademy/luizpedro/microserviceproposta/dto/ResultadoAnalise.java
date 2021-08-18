package com.zupacademy.luizpedro.microserviceproposta.dto;

import com.zupacademy.luizpedro.microserviceproposta.model.Status;

public class ResultadoAnalise{

    private String documento;
    private String nome;
    private String idProposta;
    private Status resultadoSolicitacao;



    public ResultadoAnalise(String documento, String nome, String idProposta, Status resultadoSolicitacao) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
    }

    public Status getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    @Override
    public String toString() {
        return "ResultadoAnalise{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", idProposta='" + idProposta + '\'' +
                ", status=" + resultadoSolicitacao +
                '}';
    }
}
