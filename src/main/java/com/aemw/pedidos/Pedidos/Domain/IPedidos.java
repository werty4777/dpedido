package com.aemw.pedidos.Pedidos.Domain;


import com.aemw.pedidos.Pedidos.Infrastructure.api.Models.ModelOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidos extends JpaRepository<orderP,Integer> {


    @Query(value = "select op.id_order as order_number,op.date_order as date_order,sum(od.quantity) as quantity_product,op.total_order as total_order,\n" +
            "op.status_order as status_order\n" +
            "\n" +
            " from orderp op inner join customer ct on (op.id_customer=ct.id_customer)\n" +
            "inner join order_detail od on (od.order_number=op.id_order)\n" +
            "inner join product pro on (pro.id_product=od.id_product)\n" +
            "  where ct.id_customer=?  group by order_number;", nativeQuery = true)
    List<ModelOrder> getOrder(int a);

}
