package com.AtividadeCrud.example.demo.repository;

import com.AtividadeCrud.example.demo.entity.UfEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UfRepository extends JpaRepository<UfEntity, Long> {

    Optional<UfEntity> findFirstBySigla(String sigla);
}
