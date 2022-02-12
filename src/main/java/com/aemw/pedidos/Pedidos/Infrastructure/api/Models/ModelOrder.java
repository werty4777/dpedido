package com.aemw.pedidos.Pedidos.Infrastructure.api.Models;

import java.math.BigDecimal;

public interface ModelOrder {





    String getDate_order();
    int getQuantity_product();
    BigDecimal getTotal_order();
    String getStatus_order();



    int getOrder_number();
}
