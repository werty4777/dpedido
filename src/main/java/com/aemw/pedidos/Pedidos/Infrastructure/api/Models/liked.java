package com.aemw.pedidos.Pedidos.Infrastructure.api.Models;

import java.math.BigDecimal;

public class liked {


    private String product_description;

    private int id_product;
    private String product_name;

    private String image;

    private BigDecimal unite_price;
    private BigDecimal rating;

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getUnite_price() {
        return unite_price;
    }

    public void setUnite_price(BigDecimal unite_price) {
        this.unite_price = unite_price;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}
