package com.zupacademy.luizpedro.microserviceproposta.model;

public enum Status {
    COM_RESTRICAO{
        StatusResultado getStatusResultado(){
            return StatusResultado.NAO_ELEGIVEL;
        }
    },SEM_RESTRICAO {
        StatusResultado getStatusResultado() {
            return StatusResultado.ELEGIVEL;
        }
    };

        abstract StatusResultado getStatusResultado();
}
