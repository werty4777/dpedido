package com.aemw.pedidos.Cart.Infrastructure.api;


import com.aemw.pedidos.Cart.Application.CartUses;
import com.aemw.pedidos.Cart.Domain.IDetalCart;
import com.aemw.pedidos.Cart.Infrastructure.api.Models.ICartDetails;
import com.aemw.pedidos.Cart.Infrastructure.api.Models.modelDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/cart")
public class CartController {



    private CartUses cartUses;

    private IDetalCart iDetalCart;

    @Autowired
    public CartController(CartUses cartUses, IDetalCart iDetalCart) {
        this.cartUses = cartUses;
        this.iDetalCart = iDetalCart;
    }

    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody modelDetail model) {
        cartUses.addCart(model);
        return new ResponseEntity<>(null, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<?> getCart() {
        List<ICartDetails> cart = cartUses.getCart();

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity<?> comprar() {


        return new ResponseEntity<>(cartUses.buy(), HttpStatus.OK);

    }

    @PutMapping("/{id}/{cnt}")
    public ResponseEntity<?> update(@PathVariable("id") int a, @PathVariable("cnt") int b) {
        return cartUses.updateCart(a, b);

    }

    @DeleteMapping()
    public ResponseEntity<?> delete() {
        return cartUses.eliminarCarrito();

    }


}
