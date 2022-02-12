package com.aemw.pedidos.Cart.Domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class ,property = "id_cart")
public class cart implements Serializable {
    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cart;


    private int idcustomer;


    @Column(precision = 10, scale = 6)
    private BigDecimal total_price;

    @Column
    private int number_products;

    @OneToMany(mappedBy = "idcart")
    private List<cartDetail> detailList = new ArrayList<>();


    public List<cartDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<cartDetail> detailList) {
        this.detailList = detailList;
    }

    public int getId_cart() {
        return id_cart;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id_cart, idcustomer, total_price, number_products, detailList);
    }


    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }



    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public int getNumber_products() {
        return number_products;
    }

    public void setNumber_products(int number_products) {
        this.number_products = number_products;
    }
}
