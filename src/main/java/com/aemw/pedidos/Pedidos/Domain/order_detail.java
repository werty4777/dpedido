package com.aemw.pedidos.Pedidos.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class order_detail implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detail;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_number")
    @JsonIgnore
    private orderP order_number;

    @Column
    private int quantity;


    private int id_product;

    @Column(precision = 10, scale = 5)
    private BigDecimal unit_price;


    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public orderP getOrder_number() {
        return order_number;
    }

    public void setOrder_number(orderP order_number) {
        this.order_number = order_number;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }


}


