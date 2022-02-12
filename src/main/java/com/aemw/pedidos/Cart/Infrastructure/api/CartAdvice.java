package com.aemw.pedidos.Cart.Infrastructure.api;


import com.aemw.pedidos.Cart.Domain.Exception.ExpcetionCart;
import com.aemw.pedidos.exception.ExceptionGlobal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class CartAdvice {

    @ExceptionHandler({ExpcetionCart.class})
    public ResponseEntity<?> errorCart(ExpcetionCart ex){


        ExceptionGlobal global =new ExceptionGlobal();
        global.setMessage(ex.getMessage());
        global.setDetails(Arrays.asList(ex.getCause().getMessage(),"Error ubicate in"+ex.getClass()));

        return new ResponseEntity<>(global, HttpStatus.BAD_REQUEST);
    }
}
