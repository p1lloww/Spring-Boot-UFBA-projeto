package repository;

import entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

    Optional<Ingrediente> findByNome(String nome);
    List<Ingrediente> findAllByNomeContainingIgnoreCase(String nome);

    boolean existsByNome(String nome);

    List<Ingrediente> findByEstoqueAtualLessThanAndEstoqueMinimo(Double estoqueAtualIsLessThan, Double estoqueMinimo);
    @Query("SELECT i FROM Ingrediente i WHERE i.estoqueAtual < i.estoqueMinimo")
    List<Ingrediente> findIngredientesComEstoqueAbaixoDoMinimo();
    List<Ingrediente> findAllByEstoqueAtualLessThan(Double estoqueAtualIsLessThan);

    List<Ingrediente> findByUnidade(String unidade);
    List<Ingrediente> findByUnidadeIn(List<String> unidades);

    List<Ingrediente> findAllByOrderByNomeAsc();
    List<Ingrediente> findAllByOrderByEstoqueAtualAsc();

    @Query("SELECT i FROM Ingrediente i WHERE i.estoqueAtual <= i.estoqueMinimo * 1.2")
    List<Ingrediente> findIngredientesComEstoqueBaixo();

    @Query("SELECT i FROM Ingrediente i WHERE i.estoqueAtual <= 0")
    List<Ingrediente> findIngredientesEmFalta();

    @Query("SELECT i FROM Ingrediente i WHERE i.estoqueAtual >= i.estoqueMinimo")
    List<Ingrediente> findIngredientesComEstoqueSuficiente();




}
