package com.aemw.pedidos.Pedidos.Application.events;


import com.aemw.pedidos.Cart.Domain.Exception.ExpcetionCart;
import com.aemw.pedidos.Cart.Domain.ICart;
import com.aemw.pedidos.Cart.Domain.cart;
import com.aemw.pedidos.Pedidos.Domain.IPedidos;
import com.aemw.pedidos.Pedidos.Domain.orderP;
import com.aemw.pedidos.Util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;


import javax.persistence.PrePersist;
import java.util.Optional;

public class OrderListener {
    @Autowired
    ICart iCart;

    @Autowired
    IPedidos iOrder;

    @Autowired
    Utils utils;

    @PrePersist
    private void beforeBuy(orderP order){
        Optional<cart> byIdcustomer = iCart.findByIdcustomer(getId());
        cart cart = byIdcustomer.orElseThrow(() -> new ExpcetionCart("Cart empty"));


    }

    private int getId(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();


        return utils.getUser();
    }
}
