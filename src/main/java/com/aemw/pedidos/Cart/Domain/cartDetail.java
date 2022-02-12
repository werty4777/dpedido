package com.aemw.pedidos.Cart.Domain;


import com.aemw.pedidos.Cart.Application.Events.CartListener;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@EntityListeners(CartListener.class)
@Entity(name = "cart_detail")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class ,property = "id_cart")
public class cartDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcart")
    private cart idcart;


    @Column
    private int id_product;

    @Column
    private int quantity;

    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public cart getId_cart() {
        return idcart;
    }

    public void setId_cart(cart id_cart) {
        this.idcart = id_cart;
    }

    public cart getIdcart() {
        return idcart;
    }

    public void setIdcart(cart idcart) {
        this.idcart = idcart;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        cartDetail that = (cartDetail) o;
        return id_detail == that.id_detail &&
                quantity == that.quantity &&
                Objects.equals(idcart, that.idcart) &&
                Objects.equals(id_product, that.id_product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_detail, idcart, id_product, quantity);
    }
}
