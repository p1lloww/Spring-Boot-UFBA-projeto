package repository;

import entity.EntradaEstoque;
import entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EntradaEstoqueRepository extends JpaRepository<EntradaEstoque, Long> {

    List<EntradaEstoque> findByIngrediente(Ingrediente ingrediente);
    List<EntradaEstoque> findByIngredienteId(Long ingredienteId);

    List<EntradaEstoque> findByDataEntradaBetween(LocalDateTime inicio, LocalDateTime fim);
    List<EntradaEstoque> findByDataEntradaAfter(LocalDateTime data);

    @Query("SELECT e FROM EntradaEstoque e WHERE DATE(e.dataEntrada) = CURRENT_DATE")
    List<EntradaEstoque> findEntradasDoDia();

    List<EntradaEstoque> findByQuantidadeGreaterThan(Double quantidade);

    List<EntradaEstoque> findByFornecedorContainingIgnoreCase(String fornecedor);

    List<EntradaEstoque> findAllByOrderByDataEntradaDesc();
    List<EntradaEstoque> findByIngredienteIdOrderByDataEntradaDesc(Long ingredienteId);

    @Query("SELECT e.ingrediente.nome, SUM(e.quantidade) FROM EntradaEstoque e GROUP BY e.ingrediente.id, e.ingrediente.nome")
    List<Object[]> findTotalComprasPorIngrediente();

}
