package repository;

import entity.PratoPedido;
import entity.Pedido;
import entity.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PratoPedidoRepository extends JpaRepository<PratoPedido, Long> {

    List<PratoPedido> findByPedido(Pedido pedido);
    List<PratoPedido> findByPedidoId(Long pedidoId);

    List<PratoPedido> findByPrato(Prato prato);
    List<PratoPedido> findByPratoId(Long pratoId);

    List<PratoPedido> findByQuantidadeGreaterThan(Integer quantidade);

    void deleteByPedidoId(Long pedidoId);

    @Query("SELECT ip.prato.nome, SUM(ip.quantidade) FROM PratoPedido ip GROUP BY ip.prato.id, ip.prato.nome ORDER BY SUM(ip.quantidade) DESC")
    List<Object[]> findPratosMaisPedidos();

    @Query("SELECT ip FROM PratoPedido ip WHERE DATE(ip.pedido.dataHora) = CURRENT_DATE")
    List<PratoPedido> findItensPedidosDoDia();

    @Query("SELECT ip.prato.nome, SUM(ip.subtotal) FROM PratoPedido ip GROUP BY ip.prato.id, ip.prato.nome ORDER BY SUM(ip.subtotal) DESC")
    List<Object[]> findReceitaPorPrato();

}
