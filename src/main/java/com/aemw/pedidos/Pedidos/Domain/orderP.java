package com.aemw.pedidos.Pedidos.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class orderP implements Serializable {


    @OneToMany(mappedBy = "order_number")
    List<order_detail> detailList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;
    @Column(length = 40)
    private String date_order;
    @Column(length = 40)
    private String send_date;
    @Column(scale = 4, precision = 10)
    private BigDecimal total_order;

    public List<order_detail> getDetailList() {
        return detailList;
    }


    @OneToOne
    @JoinColumn(name = "status_order")
    private status_order status_order;




    @Column(nullable = false)
    @org.hibernate.annotations.ColumnDefault("0")
    private Integer idemploye;

    public Integer getIdemploye() {
        return idemploye;
    }

    public void setIdemploye(Integer idemploye) {
        this.idemploye = idemploye;
    }

    @Column
    private int idCustomer;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public status_order getStatus_order() {
        return status_order;
    }

    public void setStatus_order(status_order status_order) {
        this.status_order = status_order;
    }


    public void setDetailList(List<order_detail> detailList) {
        this.detailList = detailList;
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

    public String getSend_date() {
        return send_date;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public BigDecimal getTotal_order() {
        return total_order;
    }

    public void setTotal_order(BigDecimal total_order) {
        this.total_order = total_order;
    }
}
