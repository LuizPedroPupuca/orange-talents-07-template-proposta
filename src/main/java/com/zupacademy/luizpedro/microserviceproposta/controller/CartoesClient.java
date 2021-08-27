package com.zupacademy.luizpedro.microserviceproposta.controller;

import com.zupacademy.luizpedro.microserviceproposta.dto.BloqueioRequest;
import com.zupacademy.luizpedro.microserviceproposta.dto.BloqueioResponse;
import com.zupacademy.luizpedro.microserviceproposta.dto.CartaoApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartoes", url = "${accounts.host}")
public interface CartoesClient {
    @GetMapping("${accounts.associaCartao}")
    CartaoApiResponse buscaCartaoParaAssociar(@RequestParam String idProposta);

    @PostMapping("${accounts.bloqueiaCartao}")
    BloqueioResponse bloqueiaCartao(@PathVariable String id, @RequestBody BloqueioRequest request);
}
