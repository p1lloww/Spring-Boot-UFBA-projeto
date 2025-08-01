package repository;

import entity.Ingrediente;
import entity.Pedido;
import entity.SaidaEstoque;
import enums.MotivoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SaidaEstoqueRepository extends JpaRepository<SaidaEstoque, Long> {

    List<SaidaEstoque> findByIngrediente(Ingrediente ingrediente);
    List<SaidaEstoque> findByIngredienteId(Long ingredienteId);

    List<SaidaEstoque> findByPedido(Pedido pedido);
    List<SaidaEstoque> findByPedidoId(Long pedidoId);

    List<SaidaEstoque> findByDataSaidaBetween(LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT s FROM SaidaEstoque s WHERE DATE(s.dataSaida) = CURRENT_DATE")
    List<SaidaEstoque> findSaidasDoDia();

    List<SaidaEstoque> findByMotivo(MotivoEnum tipo);

    @Query("SELECT s.ingrediente.nome, SUM(s.quantidade) FROM SaidaEstoque s WHERE s.motivo = 'VENDA' GROUP BY s.ingrediente.id, s.ingrediente.nome")
    List<Object[]> findConsumoVendaPorIngrediente();

    @Query("SELECT s.ingrediente.nome, SUM(s.quantidade) FROM SaidaEstoque s WHERE s.motivo IN ('PERDA', 'AJUSTE') GROUP BY s.ingrediente.id, s.ingrediente.nome")
    List<Object[]> findPerdasEAjustesPorIngrediente();

}
