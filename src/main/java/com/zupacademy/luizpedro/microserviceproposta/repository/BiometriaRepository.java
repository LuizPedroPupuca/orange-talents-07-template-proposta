package com.zupacademy.luizpedro.microserviceproposta.repository;

import com.zupacademy.luizpedro.microserviceproposta.model.Biometria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}
