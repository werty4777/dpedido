package com.aemw.pedidos.Cart.Domain.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExpcetionCart extends RuntimeException {
    public ExpcetionCart(String s) {
    }
}
