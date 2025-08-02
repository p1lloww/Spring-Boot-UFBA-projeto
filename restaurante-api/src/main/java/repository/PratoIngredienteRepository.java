package repository;

import backup.Ingrediente;
import entity.Prato;
import backup.PratoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PratoIngredienteRepository extends JpaRepository<PratoIngrediente, Long> {

    List<PratoIngrediente> findByPrato(Prato prato);
    List<PratoIngrediente> findByPratoId(Long pratoId);

    List<PratoIngrediente> findByIngrediente(Ingrediente ingrediente);
    List<PratoIngrediente> findByIngredienteId(Long ingredienteId);

    List<PratoIngrediente> findByQuantidadeGreaterThan(Double quantidade);
    List<PratoIngrediente> findByQuantidadeBetween(Double min, Double max);

    Optional<PratoIngrediente> findByPratoAndIngrediente(Prato prato, Ingrediente ingrediente);
    boolean existsByPratoIdAndIngredienteId(Long pratoId, Long ingredienteId);

    void deleteByPratoId(Long pratoId);
    void deleteByIngredienteId(Long ingredienteId);

    @Query("SELECT pi.ingrediente.nome, SUM(pi.quantidade) FROM PratoIngrediente pi GROUP BY pi.ingrediente.id, pi.ingrediente.nome ORDER BY SUM(pi.quantidade) DESC")
    List<Object[]> findIngredientesMaisUsados();

    @Query("SELECT pi.prato.nome, COUNT(pi) FROM PratoIngrediente pi GROUP BY pi.prato.id, pi.prato.nome ORDER BY COUNT(pi) DESC")
    List<Object[]> findPratosComMaisIngredientes();

}
