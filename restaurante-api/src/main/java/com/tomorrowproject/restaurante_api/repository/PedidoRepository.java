package com.tomorrowproject.restaurante_api.repository;

import com.tomorrowproject.restaurante_api.entity.Cliente;
import com.tomorrowproject.restaurante_api.entity.Pedido;
import com.tomorrowproject.restaurante_api.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);
    List<Pedido> findByClienteId(Long clienteId);

    List<Pedido> findByStatus(StatusPedido status);
    List<Pedido> findByStatusIn(List<StatusPedido> statuses);

    List<Pedido> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    List<Pedido> findByDataHoraAfter(LocalDateTime data);

    List<Pedido> findByTotalBetween(BigDecimal min, BigDecimal max);
    List<Pedido> findByTotalGreaterThan(BigDecimal valor);

    List<Pedido> findByStatusOrderByDataHoraAsc(StatusPedido status);

}
