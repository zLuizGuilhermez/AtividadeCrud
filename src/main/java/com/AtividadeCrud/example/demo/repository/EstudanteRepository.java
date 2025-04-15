package com.AtividadeCrud.example.demo.repository;

import com.AtividadeCrud.example.demo.entity.EstudanteEntity;
import com.AtividadeCrud.example.demo.entity.UfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstudanteRepository extends JpaRepository<EstudanteEntity, Long> {

    Optional<EstudanteEntity> findFirstByMatricula(String matricula);
}
