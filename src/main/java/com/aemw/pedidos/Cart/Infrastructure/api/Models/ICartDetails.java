package com.aemw.pedidos.Cart.Infrastructure.api.Models;

import java.math.BigDecimal;

public interface ICartDetails {
     String getProduct_name();
     String getImage();
     int getQuantity();
     BigDecimal getUnite_price();
     int getId_product();
}
