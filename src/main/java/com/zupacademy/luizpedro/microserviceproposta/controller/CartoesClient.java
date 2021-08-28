package com.zupacademy.luizpedro.microserviceproposta.controller;

import com.zupacademy.luizpedro.microserviceproposta.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartoes", url = "${accounts.host}")
public interface CartoesClient {
    @GetMapping("${accounts.associaCartao}")
    CartaoApiResponse buscaCartaoParaAssociar(@RequestParam String idProposta);

    @PostMapping("${accounts.bloqueiaCartao}")
    BloqueioResponse bloqueiaCartao(@PathVariable String id, @RequestBody BloqueioRequest request);

    @PostMapping("${accounts.avisaViagemCartao}")
    AvisoDeViagemApiResponse avisaViagem(@PathVariable String id, @RequestBody AvisoDeViagemApiRequest request);
}
