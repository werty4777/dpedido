package com.aemw.pedidos.Pedidos.Infrastructure.api.Models;

public class PedidosDTO {
    private int id_order;
    private String date_order;
    private String address;
    private String phone;
    private String name;


    public PedidosDTO(int id_order, String date_order, String address, String phone, String name) {
        this.id_order = id_order;
        this.date_order = date_order;
        this.address = address;
        this.phone = phone;
        this.name = name;

    }

    public PedidosDTO() {
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
