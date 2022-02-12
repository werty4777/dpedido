package com.aemw.pedidos.Pedidos.Domain;

import java.math.BigDecimal;

public interface DetailsDTO {

    String getId_detail();
    int getOrder_number();
    int getQuantity();
    BigDecimal getUnite_price();
    int getId_product();
}
