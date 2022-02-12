package com.aemw.pedidos.Pedidos.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatus  extends JpaRepository<status_order,Integer> {
}
