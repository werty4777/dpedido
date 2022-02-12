package com.aemw.pedidos.Pedidos.Domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class pedidoEntregados implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identregado;

    public int getIdentregado() {
        return identregado;
    }

    public void setIdentregado(int identregado) {
        this.identregado = identregado;
    }

    @OneToOne
    @JoinColumn(name = "idpedido")
    private orderP idpedido;


    private String foto;

    public orderP getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(orderP idpedido) {
        this.idpedido = idpedido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
