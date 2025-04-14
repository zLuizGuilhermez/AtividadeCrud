package com.AtividadeCrud.example.demo.Repository;

import com.AtividadeCrud.example.demo.entity.CidadeEntity;
import com.AtividadeCrud.example.demo.entity.UfEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {

    Optional<CidadeEntity> findFirstByNome(String nome);
}
