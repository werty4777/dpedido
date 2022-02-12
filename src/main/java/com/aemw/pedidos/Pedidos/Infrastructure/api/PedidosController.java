package com.aemw.pedidos.Pedidos.Infrastructure.api;

import com.aemw.pedidos.Pedidos.Application.PedidosCases;
import com.aemw.pedidos.Pedidos.Infrastructure.api.Models.EntregadosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class PedidosController {


    private PedidosCases pedidosCases;

    @Autowired
    public PedidosController(PedidosCases pedidosCases) {
        this.pedidosCases = pedidosCases;
    }

    @GetMapping("/pedidosEmp/{id}")
    public ResponseEntity<?> listarPedidosEmp(@PathVariable("id") int id,@RequestHeader("Authorization") String token,@RequestParam("id") int id2){
        System.out.println("este es el token " +token );
        return pedidosCases.listarPedidosEmp(id,token,id2);

    }

    @GetMapping("/pedidosEmps/{id}")
    public ResponseEntity<?> listarPedidosEmp2(@PathVariable("id") int id,@RequestHeader("Authorization") String token){
        System.out.println("este es el token " +token );
        return pedidosCases.listarPedidosEmp(id,token,0);

    }

    @GetMapping
    public ResponseEntity<?> getOrder(){

        return new ResponseEntity<>(pedidosCases.getOrder(), HttpStatus.OK);

    }


    @GetMapping("/details")
    public ResponseEntity<?> getDetails(@RequestParam("value") int a){
        return pedidosCases.getDetail(a);


    }

    @PutMapping("/pedidoEmp/{id}")
    public ResponseEntity<?> editarPedido(@PathVariable("id") int id,@RequestHeader("Authorization") String token,@RequestParam("id")int cambio){

        return pedidosCases.actualizarPedido(id,token,cambio);

    }
    @PutMapping("/pedidoEmps")
    public ResponseEntity<?> editarPedido2( @RequestHeader("Authorization") String token, @RequestBody EntregadosDTO entregados){

        return pedidosCases.entregarPedido(token,entregados);

    }
    @GetMapping("/pedidos/lista")
    public List pedidostodos(){

        return this.pedidosCases.getOrdenes();
    }


}
