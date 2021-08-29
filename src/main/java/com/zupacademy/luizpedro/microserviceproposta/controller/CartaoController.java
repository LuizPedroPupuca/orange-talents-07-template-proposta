package com.zupacademy.luizpedro.microserviceproposta.controller;

import com.zupacademy.luizpedro.microserviceproposta.dto.*;
import com.zupacademy.luizpedro.microserviceproposta.model.*;
import com.zupacademy.luizpedro.microserviceproposta.repository.*;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private AvisoDeViagemRepository avisoDeViagemRepository;

    @Autowired
    private CarteiraDigitalRepository carteiraDigitalRepository;

    @Autowired
    private CartoesClient cartoesClient;


    @PostMapping("/{idCartao}/biometria")
    @Transactional
    public ResponseEntity<?> cadastraBiometria(@PathVariable Long idCartao, @RequestBody @Valid BiometriaRequest biometriaRequest,
                                            UriComponentsBuilder builder) {

        Optional<Cartao> cartaoOptional = cartaoRepository.findById(idCartao);

        if (cartaoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }

        Biometria biometria = biometriaRequest.toModel(cartaoOptional.get());
        biometriaRepository.save(biometria);
        URI uri = builder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{numeroCartao}/bloqueios")
    @Transactional
    public ResponseEntity<?> bloqueiaCartao(@PathVariable String numeroCartao, HttpServletRequest request) {

        Optional<Cartao> cartaoOptional = cartaoRepository.findByNumeroCartao(numeroCartao);

        String userClient = request.getHeader("User-Agent");
        String ipClient = request.getHeader("X-FORWARDED-FOR");
        if (ipClient == null)
            ipClient = request.getRemoteAddr();

        if (cartaoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }

        Cartao cartao = cartaoOptional.get();
        if (cartao.getStatus() == StatusCartao.bloqueado) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        Bloqueio bloqueio = new Bloqueio(ipClient, userClient, cartao);
        try {
            BloqueioRequest bloqueioRequest = new BloqueioRequest("Proposta");
            cartoesClient.bloqueiaCartao(numeroCartao, bloqueioRequest);
        } catch (FeignException e) {
            System.out.println("excecao: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        bloqueioRepository.save(bloqueio);
        cartao.bloqueioCartao();
        cartaoRepository.save(cartao);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{numeroCartao}/avisos")
    @Transactional
    public ResponseEntity<?> associaAvisoDeViagemCartao(@PathVariable String numeroCartao, HttpServletRequest request,
                                                 @RequestBody AvisoDeViagemRequest avisoDeViagemRequest) {

        Optional<Cartao> cartaoOptional = cartaoRepository.findByNumeroCartao(numeroCartao);

        if (cartaoOptional.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        String userClient = request.getHeader("User-Agent");
        String ipClient = request.getHeader("X-FORWARDED-FOR");
        if (ipClient == null) {
            ipClient = request.getRemoteAddr();
        }

        Cartao cartao = cartaoOptional.get();

        AvisoDeViagem avisoDeViagem = avisoDeViagemRequest.toModel(cartao,
                ipClient, userClient);

        try {
            AvisoDeViagemApiRequest avisoDeViagemApiRequest = new AvisoDeViagemApiRequest(avisoDeViagemRequest);
            AvisoDeViagemApiResponse avisoDeViagemApiResponse = cartoesClient.avisaViagem(numeroCartao, avisoDeViagemApiRequest);
        } catch (FeignException e) {
            System.out.println("excecao: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        avisoDeViagemRepository.save(avisoDeViagem);
        return ResponseEntity.ok().body("Aviso de viagem cadastrado com sucesso");
    }

    @PostMapping("/{numeroCartao}/carteiras")
    @Transactional
    public ResponseEntity associaCartaoACarteiraDigital(@PathVariable String numeroCartao,
                                                        @RequestBody CarteiraDigitalRequest carteiraDigitalRequest,
                                                        UriComponentsBuilder builder) {

        Optional<Cartao> cartaoOptional = cartaoRepository.findByNumeroCartao(numeroCartao);

        if (cartaoOptional.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND);
        }

        Cartao cartao = cartaoOptional.get();

        List<CarteiraDigital> carteirasDigitais = carteiraDigitalRepository.findByCartaoNumeroCartao(numeroCartao);
        for (CarteiraDigital carteiraDigital: carteirasDigitais) {
            if (carteiraDigitalRequest.getEmissor().equals(carteiraDigital.getEmissor())){
                return ResponseEntity.unprocessableEntity().body("Já existe um número de cartão com o mesmo emissor");
            }
        }


        CarteiraDigitalApiResponse carteiraDigitalApiResponse = null;
        try {
            CarteiraDigitalApiRequest carteiraDigitalApiRequest = new CarteiraDigitalApiRequest(carteiraDigitalRequest);
            carteiraDigitalApiResponse = cartoesClient.associaCarteira(numeroCartao, carteiraDigitalApiRequest);
            System.out.println("O número da carteira digital é "+carteiraDigitalApiResponse.getId());
        } catch (FeignException e) {
            System.out.println("excecao: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        CarteiraDigital carteiraDigital = carteiraDigitalRequest.toModel(cartao,carteiraDigitalApiResponse.getId());

        carteiraDigitalRepository.save(carteiraDigital);
        URI uri = builder.path("/carteiras/{id}").buildAndExpand(carteiraDigital.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
