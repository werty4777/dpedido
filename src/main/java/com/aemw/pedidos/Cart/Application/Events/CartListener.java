package com.aemw.pedidos.Cart.Application.Events;



import com.aemw.pedidos.Cart.Domain.Exception.ExpcetionCart;
import com.aemw.pedidos.Cart.Domain.ICart;
import com.aemw.pedidos.Cart.Domain.cart;
import com.aemw.pedidos.Cart.Domain.cartDetail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class CartListener {



    @Autowired
    ICart iCart;

    @Autowired
    EntityManager manager;

    @PrePersist
    private void beforeInsertCart(cartDetail detail){
        try {
            cart cart = detail.getId_cart();

            int id_product = detail.getId_product();
            BigDecimal add = cart.getTotal_price().add(getUnite_price(id_product).get(0).multiply(new BigDecimal(detail.getQuantity())));
            cart.setTotal_price(add);
            int numberProducts = cart.getNumber_products();
            cart.setNumber_products(numberProducts+1);


            iCart.save(cart);
        }catch (Exception ex){
            throw  new ExpcetionCart("Error in add cart");
        }

    }
    public List<BigDecimal> getUnite_price(int idproducto) {

        Query nativeQuery = manager.createNativeQuery("select unite_price from product pt where pt.id_product=?");
        nativeQuery.setParameter(1, idproducto);

        List<BigDecimal> resultList = nativeQuery.getResultList();

        return resultList;
    }

}
