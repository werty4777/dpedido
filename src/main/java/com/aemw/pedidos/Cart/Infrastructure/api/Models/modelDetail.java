package com.aemw.pedidos.Cart.Infrastructure.api.Models;

public class modelDetail
{

    private int quantity;
    private int id_product;

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
}

