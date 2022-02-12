package com.aemw.pedidos.Pedidos.Domain.Exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class PedidosException extends RuntimeException {

    public PedidosException(String message) {
        super(message);
    }
}
