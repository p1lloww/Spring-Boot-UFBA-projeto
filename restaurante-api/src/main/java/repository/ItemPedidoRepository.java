package repository;

import entity.ItemPedido;
import entity.Pedido;
import entity.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findByPedido(Pedido pedido);
    List<ItemPedido> findByPedidoId(Long pedidoId);

    List<ItemPedido> findByPrato(Prato prato);
    List<ItemPedido> findByPratoId(Long pratoId);

    List<ItemPedido> findByQuantidadeGreaterThan(Integer quantidade);

    void deleteByPedidoId(Long pedidoId);

    @Query("SELECT ip.prato.nome, SUM(ip.quantidade) FROM ItemPedido ip GROUP BY ip.prato.id, ip.prato.nome ORDER BY SUM(ip.quantidade) DESC")
    List<Object[]> findPratosMaisPedidos();

    @Query("SELECT ip FROM ItemPedido ip WHERE DATE(ip.pedido.dataHora) = CURRENT_DATE")
    List<ItemPedido> findItensPedidosDoDia();

    @Query("SELECT ip.prato.nome, SUM(ip.subtotal) FROM ItemPedido ip GROUP BY ip.prato.id, ip.prato.nome ORDER BY SUM(ip.subtotal) DESC")
    List<Object[]> findReceitaPorPrato();

}
