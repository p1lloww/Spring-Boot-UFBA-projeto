package repository;

import entity.Cliente;
import entity.Mesa;
import entity.Pedido;
import enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);
    List<Pedido> findByClienteId(Long clienteId);

    List<Pedido> findByMesa(Mesa mesa);
    List<Pedido> findByMesaNumero(Integer numeroMesa);

    List<Pedido> findByStatus(StatusPedido status);
    List<Pedido> findByStatusIn(List<StatusPedido> statuses);

    List<Pedido> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    List<Pedido> findByDataHoraAfter(LocalDateTime data);

    @Query("SELECT p FROM Pedido p WHERE DATE(p.dataHora) = CURRENT_DATE")
    List<Pedido> findPedidosDoDia();

    @Query("SELECT p FROM Pedido p WHERE p.dataHora >= :inicioSemana")
    List<Pedido> findPedidosDaSemana(@Param("inicioSemana") LocalDateTime inicioSemana);

    List<Pedido> findByTotalBetween(BigDecimal min, BigDecimal max);
    List<Pedido> findByTotalGreaterThan(BigDecimal valor);

    List<Pedido> findByStatusOrderByDataHoraAsc(StatusPedido status);

    @Query("SELECT COUNT(p), SUM(p.total) FROM Pedido p WHERE DATE(p.dataHora) = CURRENT_DATE")
    Object[] findEstatisticasDoDia();

    @Query("SELECT p.status, COUNT(p) FROM Pedido p GROUP BY p.status")
    List<Object[]> contarPedidosPorStatus();

    @Query("SELECT p.cliente, COUNT(p), SUM(p.total) FROM Pedido p GROUP BY p.cliente ORDER BY COUNT(p) DESC")
    List<Object[]> findTopClientes();

}
