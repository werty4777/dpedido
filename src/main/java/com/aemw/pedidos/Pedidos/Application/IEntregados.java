package com.aemw.pedidos.Pedidos.Application;


import com.aemw.pedidos.Pedidos.Domain.orderP;
import com.aemw.pedidos.Pedidos.Domain.pedidoEntregados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntregados  extends JpaRepository<pedidoEntregados, Integer> {}



