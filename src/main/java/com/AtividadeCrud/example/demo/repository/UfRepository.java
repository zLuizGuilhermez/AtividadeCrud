package com.AtividadeCrud.example.demo.repository;

import com.AtividadeCrud.example.demo.entity.UfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UfRepository extends JpaRepository<UfEntity, Long> {

    Optional<UfEntity> findFirstBySigla(String sigla);

    Optional<UfEntity> findFirstById(Integer id);
}
