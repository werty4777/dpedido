package com.aemw.pedidos.Pedidos.Application;

import com.aemw.pedidos.Pedidos.Application.models.listaOrdenesModelo;
import com.aemw.pedidos.Pedidos.Domain.*;
import com.aemw.pedidos.Pedidos.Domain.Exception.PedidosException;
import com.aemw.pedidos.Pedidos.Infrastructure.api.Models.EntregadosDTO;
import com.aemw.pedidos.Pedidos.Infrastructure.api.Models.ModelOrder;
import com.aemw.pedidos.Pedidos.Infrastructure.api.Models.PedidosDTO;
import com.aemw.pedidos.Pedidos.Infrastructure.api.Models.liked;
import com.aemw.pedidos.Util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PedidosCases {


    @Autowired
    Utils utils;


    @Autowired
    RestTemplate rest;

    @Autowired
    IPedidos iPedidos;


    @Autowired
    IEntregados iEntregados;

    @Autowired
    IOrderDetail iOrderDetail;

    @Autowired
    EntityManager manager;
    @Autowired
    IStatus iStatus;

    public ResponseEntity<?> listarPedidosEmp(int id, String token, int a) {
        Map<String, Object> mapa = new HashMap<>();
        try {

            mapa.put("data", pedidosDTO(id, token, a));
            return new ResponseEntity<>(mapa, HttpStatus.OK);


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new PedidosException("Error al buscar pedidos");
        }


    }

    public List<PedidosDTO> pedidosDTO(int idstatus, String token, int a) {


        if (idstatus == 1) {
            Query query = manager.createNativeQuery("select op.id_order,op.date_order,cus.address,cus.phone,cus.name,cus.last_name ,cus.province from orderp op inner join customer cus on(cus.id_customer=op.id_customer) where op.status_order=?", Tuple.class);
            query.setParameter(1, idstatus);

            return getPedidosDTOS(query, a);

        } else if (idstatus == 2) {
            Query query = manager.createNativeQuery("select op.id_order,op.date_order,cus.address,cus.phone,cus.name,cus.last_name ,cus.province from orderp op inner join customer cus on(cus.id_customer=op.id_customer) where op.status_order=? and op.idemploye=?", Tuple.class);
            query.setParameter(1, idstatus);
            query.setParameter(2, idEmploye(token));
            return getPedidosDTOS(query, a);
        } else {
            Query query = manager.createNativeQuery("select op.id_order,op.date_order,cus.address,cus.phone,cus.name,cus.last_name ,cus.province from orderp op inner join customer cus on(cus.id_customer=op.id_customer) where op.status_order=? and op.idemploye=?", Tuple.class);
            query.setParameter(1, idstatus);
            query.setParameter(2, idEmploye(token));

            return getPedidosDTOS(query, a);
        }


    }

    private List<PedidosDTO> getPedidosDTOS(Query query, int a) {
        try {
            List<Tuple> resultList = query.getResultList();

            List<PedidosDTO> listapedidos = new ArrayList<>();
            for (Tuple tup : resultList) {


                int id_order = Integer.parseInt(tup.get("id_order").toString());
                String date = tup.get(1).toString();
                String direccion = tup.get(2).toString();
                String phone = tup.get(3).toString();
                String name = tup.get(4).toString();
                String lastname = tup.get(5).toString();
                String province = tup.get(6).toString();
                PedidosDTO dto = new PedidosDTO(id_order, date, direccion + " " + province, phone, name + " " + lastname);
                listapedidos.add(dto);

            }
            int index = 0;

            if (a == 0) {
                index = (a * 10);
            } else if (a > 0) {
                index = (a * 10);
            }

            if ((index + 10) > listapedidos.size()) {

                List<PedidosDTO> pedidosDTOS = listapedidos.subList(index, listapedidos.size());
                return pedidosDTOS;
            } else {

                List<PedidosDTO> pedidosDTOS = listapedidos.subList(index, index + 10);
                return pedidosDTOS;
            }

        } catch (Exception ex) {
            return new ArrayList<>();
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> actualizarPedido(int idpedido, String token, int idcambio) {

        try {

            int i = idEmploye(token);
            System.out.println("mi id es " + i);
            Optional<orderP> byId = iPedidos.findById(idpedido);
            orderP pedidp = byId.orElseThrow(() -> new RuntimeException("Error pedido no encontrado"));
            pedidp.setIdemploye(i);

            System.out.println(pedidp.getId_order());
            System.out.println(pedidp.getStatus_order().getId_status());

            Optional<status_order> byId1 = iStatus.findById(idcambio);
            status_order status = byId1.orElseThrow(() -> new RuntimeException("Error al buscar estado pedido"));
            pedidp.setStatus_order(status);

            iPedidos.save(pedidp);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al actuazliar pedido");
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> entregarPedido(String token, EntregadosDTO entregados) {

        try {

            actualizarPedido(entregados.getIdpedido(), token, 3);
            Optional<orderP> byId = iPedidos.findById(entregados.getIdpedido());
            orderP pedido = byId.orElseThrow(() -> new RuntimeException("Error pedido no encontrado"));

            pedidoEntregados pd = new pedidoEntregados();
            pd.setIdpedido(pedido);
            pd.setFoto(entregados.getFoto());

            iEntregados.save(pd);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {

            throw new RuntimeException("Error al entregar el pedido");
        }


    }

    public List<ModelOrder> getOrder() {


        try {


            return iPedidos.getOrder(utils.getUser());

        } catch (Exception ex) {
            ex.printStackTrace();

            throw new PedidosException("Orders not found");
        }


    }

    public ResponseEntity<?> getDetail(int order_number) {

        try {

            int user = utils.getUser();

            List<DetailsDTO> details = iOrderDetail.getDetails(SecurityContextHolder.getContext().getAuthentication().getName(), order_number);


            if (details.isEmpty()) {
                throw new PedidosException("User not have orders");
            }
            List<Map<String, Object>> data = new ArrayList<>();

            details.forEach(ord -> {

                Map<String, Object> product = new HashMap<>();

                List<liked> likeds = ratingProduct(ord.getId_product());
                product.put("id_product", likeds.get(0).getId_product());
                product.put("unite_price", likeds.get(0).getUnite_price());
                product.put("product_name", likeds.get(0).getProduct_name());
                product.put("rating", likeds.get(0).getRating());
                product.put("product_description", likeds.get(0).getProduct_description());
                product.put("image", likeds.get(0).getImage());
                product.put("quantity", ord.getQuantity());
                data.add(product);

            });


            Map<String, Object> datas = new HashMap<>();
            datas.put("data", data);


            return new ResponseEntity<>(datas, HttpStatus.OK);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new PedidosException("Error al obtener detalles de la orden");
        }
    }


    public List<liked> ratingProduct(int id) {

        System.out.println("estoy buscando");
        try {


            ResponseEntity<List<liked>> exchange = rest.exchange("http://productoservice/rating/lista/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<List<liked>>() {
            });


            return exchange.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public int idEmploye(String token) {

        //and op.idemploye=
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);

            HttpEntity<String> entity = new HttpEntity<String>(null, headers);


            ResponseEntity<Integer> exchange = rest.exchange("http://accountservice/user/id", HttpMethod.GET, entity, Integer.class);

            Integer body = exchange.getBody();
            System.out.println("el numero es " + body);
            return body;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error un get id employe");
        }

    }

    public List getOrdenes() {

        return this.iPedidos.findAll().stream().map(orderP -> {

            listaOrdenesModelo modelo = new listaOrdenesModelo();
            modelo.setIdOrden(orderP.getId_order());
            modelo.setFechaOrden(orderP.getDate_order());
            modelo.setFechaEntrega(orderP.getSend_date());
            modelo.setEstado(orderP.getStatus_order().getStatus());
            modelo.setTotal(orderP.getTotal_order());
            return modelo;

        }).collect(Collectors.toList());

    }
}
