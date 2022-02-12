package com.aemw.pedidos.Cart.Application;


import com.aemw.pedidos.Cart.Domain.Exception.ExpcetionCart;
import com.aemw.pedidos.Cart.Domain.ICart;
import com.aemw.pedidos.Cart.Domain.IDetalCart;
import com.aemw.pedidos.Cart.Infrastructure.api.Models.ICartDetails;
import com.aemw.pedidos.Cart.Infrastructure.api.Models.detailModel;
import com.aemw.pedidos.Cart.Infrastructure.api.Models.modelDetail;
import com.aemw.pedidos.Cart.Domain.cart;
import com.aemw.pedidos.Cart.Domain.cartDetail;
import com.aemw.pedidos.Pedidos.Domain.Exception.PedidosException;
import com.aemw.pedidos.Pedidos.Domain.*;
import com.aemw.pedidos.Util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartUses {

    final
    IPedidos iOrder;
    final
    IOrderDetail iOrderDetail;

    final
    IDetalCart iDetalCart;

    final
    EntityManager manager;

    @Autowired
    Utils utils;


    final
    ICart iCart;


    final
    IStatus iStatus;

    public CartUses(EntityManager manager, ICart iCart, IDetalCart iDetalCart, IPedidos iOrder, IOrderDetail iOrderDetail, IStatus iStatus) {
        this.manager = manager;

        this.iCart = iCart;

        this.iDetalCart = iDetalCart;
        this.iOrder = iOrder;
        this.iOrderDetail = iOrderDetail;

        this.iStatus = iStatus;
    }

    public String getId() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();


        return name;
    }


    @Transactional(rollbackFor = Exception.class)
    public void addCart(modelDetail cartDetail) {

        try {


            int user = utils.getUser();


            Optional<cart> byIdcustomer = iCart.findByIdcustomer(user);

            if (!byIdcustomer.isPresent()) {

                cart a = new cart();


                a.setIdcustomer(user);
                a.setNumber_products(0);
                a.setTotal_price(BigDecimal.ZERO);
                cart save = iCart.save(a);

                cartDetail detalles = new cartDetail();
                detalles.setId_cart(save);
                int id_product = cartDetail.getId_product();
                detalles.setId_product(id_product);
                detalles.setQuantity(cartDetail.getQuantity());
                iDetalCart.save(detalles);


            } else {


                List<detailModel> cart = iDetalCart.getCart(user, cartDetail.getId_product());


                if (cart.isEmpty()) {
                    int id_product = cartDetail.getId_product();

                    cartDetail cartDetail1 = new cartDetail();
                    cartDetail1.setId_cart(byIdcustomer.get());
                    cartDetail1.setQuantity(cartDetail.getQuantity());
                    cartDetail1.setId_product(id_product);

                    iDetalCart.save(cartDetail1);
                } else {
                    int id_product = cartDetail.getId_product();
                    cartDetail cartDetail1 = new cartDetail();
                    cartDetail1.setId_detail(cart.get(0).getId_detail());
                    cartDetail1.setId_cart(byIdcustomer.get());
                    cartDetail1.setQuantity(cart.get(0).getQuantity() + cartDetail.getQuantity());
                    cartDetail1.setId_product(id_product);

                    iDetalCart.save(cartDetail1);

                }


            }

        } catch (Exception ex) {


            throw new ExpcetionCart("error in load cart data try again please");

        }


    }

    public List<ICartDetails> getCart() {
        try {

            int user = utils.getUser();

            return iDetalCart.getDetails(user);


        } catch (Exception ex) {
            throw new ExpcetionCart("error in get all data shop cart try again please");
        }


    }


    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> buy() {

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String format = date.format(formatter);

        String email = getId();


        int user = utils.getUser();


        Optional<cart> cartop = iCart.findByIdcustomer(user);
        cart cart = cartop.orElseThrow(() -> new PedidosException("Error get Cart"));
        List<cartDetail> byIdcart = iDetalCart.findByIdcart(cart);

        if (byIdcart.size() == 0) {
            throw new ExpcetionCart("Cart not have items");
        }


        orderP order = new orderP();

        order.setDate_order(format);
        order.setSend_date(format);
        BigDecimal price = new BigDecimal(0);

        for (cartDetail cartDetail : byIdcart) {

            int id_product = cartDetail.getId_product();
            List<BigDecimal> unite_price = getUnite_price(id_product);
            price = price.add(unite_price.get(0).multiply(new BigDecimal(cartDetail.getQuantity())));
        }


        order.setTotal_order(price);
        Optional<status_order> byId = iStatus.findById(1);
        order.setStatus_order(byId.get());
        order.setIdCustomer(user);
        orderP save = iOrder.save(order);

        byIdcart.forEach(detail -> {
            order_detail order_detail = new order_detail();
            order_detail.setId_product(detail.getId_product());

            order_detail.setId_product(detail.getId_product());
            order_detail.setOrder_number(save);
            order_detail.setQuantity(detail.getQuantity());
            int id_product = detail.getId_product();
            List<BigDecimal> unite_price = getUnite_price(id_product);
            order_detail.setUnit_price(unite_price.get(0));
            iOrderDetail.save(order_detail);
        });
        iCart.delete(cart);
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("message", "buy successfully");
        return mapa;


    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> updateCart(int idProduct, int nuevaCantidad) {
        try {

            System.out.println(idProduct);

            cart cart = Cart();
            List<cartDetail> detailList = cart.getDetailList();


            List<BigDecimal> unite_price = getUnite_price(idProduct);

            if (unite_price.size() == 0) {
                throw new RuntimeException("Error in get product");
            }


            detailList.forEach(detail -> {
                System.out.println(detail.getId_product());
                int i = detail.getQuantity() + nuevaCantidad;
                if (i != 0 && nuevaCantidad != 0) {

                    if(detail.getId_product()==idProduct){
                        detail.setQuantity(i);
                    }

                }
                if (nuevaCantidad == 0) {

                    if(detail.getId_product()==idProduct){
                        iDetalCart.deleteById(detail.getId_detail());
                    }

                }


            });
            cart.setDetailList(detailList);

            iCart.save(cart);
            return new ResponseEntity<>(HttpStatus.OK);


        } catch (Exception ex) {

            ex.printStackTrace();
            throw new ExpcetionCart("Error in update cart ");
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> eliminarCarrito() {
        try {


            iCart.delete(Cart());

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception ex) {
            throw new ExpcetionCart("Error in delete cart");
        }
    }

    public cart Cart() {
        int dataUser = utils.getUser();


        Optional<cart> cartop = iCart.findByIdcustomer(dataUser);
        cart cart = cartop.orElseThrow(() -> new RuntimeException("Error get Cart"));
        return cart;
    }


    public List<BigDecimal> getUnite_price(int idproducto) {

        Query nativeQuery = manager.createNativeQuery("select unite_price from product pt where pt.id_product=?");
        nativeQuery.setParameter(1, idproducto);

        List<BigDecimal> resultList = nativeQuery.getResultList();

        return resultList;
    }


}
