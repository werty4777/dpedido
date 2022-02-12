package com.aemw.pedidos.Cart.Domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICart  extends JpaRepository<cart,Integer> {




   Optional<cart> findByIdcustomer(int a );


}
