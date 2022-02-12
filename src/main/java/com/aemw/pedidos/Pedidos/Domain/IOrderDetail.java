package com.aemw.pedidos.Pedidos.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDetail extends JpaRepository<order_detail,Integer> {
    @Query(value = "SELECT distinct  lista.id_detail,lista.order_number,lista.quantity,lista.id_product,lista.unit_price from login lo inner join customer cus inner join orderp op on (op.id_customer=cus.id_customer) inner join order_detail lista on(lista.order_number=op.id_order) where lo.email=? and op.id_order=?",nativeQuery = true)
    List<DetailsDTO> getDetails(String email, int id_order);
}
