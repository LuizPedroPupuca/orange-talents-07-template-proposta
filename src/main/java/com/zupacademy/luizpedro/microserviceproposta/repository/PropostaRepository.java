package com.zupacademy.luizpedro.microserviceproposta.repository;

import com.zupacademy.luizpedro.microserviceproposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    Optional<Proposta> findByNome(String nome);

    @Query("SELECT p FROM Proposta p WHERE p.statusResultado = 'ELEGIVEL' and p.cartao = null")
    List<Proposta> findEligibleProposesWithoutCard();
}
