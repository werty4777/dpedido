package com.aemw.pedidos.Pedidos.Infrastructure.api.Models;

public class EntregadosDTO {


    private int idpedido;


    private String foto;

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
