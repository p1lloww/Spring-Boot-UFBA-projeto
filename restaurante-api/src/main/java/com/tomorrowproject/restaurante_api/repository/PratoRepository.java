package com.tomorrowproject.restaurante_api.repository;

import com.tomorrowproject.restaurante_api.entity.Prato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PratoRepository extends JpaRepository<Prato, Long> {
    //List<Prato> findByCategoria(Categoria categoria);
    //List<Prato> findByCategoriaId(Long categoriaId);

    List<Prato> findByNomeContainingIgnoreCase(String nome);

    Optional<Prato> findByNome(String nome);

    List<Prato> findByPrecoBetween(BigDecimal precoAfter, BigDecimal precoBefore);
    List<Prato> findByPrecoLessThan(BigDecimal precoIsLessThan);
    List<Prato> findByPrecoGreaterThan(BigDecimal precoIsGreaterThan);

    List<Prato> findByTempoDePreparo(Integer tempoDePreparo);
    List<Prato> findByTempoDePreparoBetween(Integer tempoDePreparoAfter, Integer tempoDePreparoBefore);
    List<Prato> findByTempoDePreparoLessThan(Integer tempoDePreparoIsLessThan);

    List<Prato> findAllByOrderByPrecoAsc();
    List<Prato> findAllByOrderByPrecoDesc();
    List<Prato> findAllByOrderByNomeAsc();
    //List<Prato> findAllByCategoriaIdOrderByPrecoAsc(Long categoriaId);

    List<Prato> findTop5ByOrderByPrecoAsc();
    List<Prato> findTop10ByOrderByPrecoDesc();


}
