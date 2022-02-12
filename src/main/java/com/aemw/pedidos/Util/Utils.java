package com.aemw.pedidos.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
public class Utils {


    @Autowired
    EntityManager manager;

    private BCryptPasswordEncoder encoder;



    public int getUser(){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        Query nativeQuery = manager.createNativeQuery("select cus.id_customer from login  log inner join customer cus on(cus.iduser=log.id_user) where log.email=?");

        nativeQuery.setParameter(1,name);

        List<Integer> resultList = nativeQuery.getResultList();

        return resultList.get(0);



    }


    public String encode(String pw) {
        encoder = new BCryptPasswordEncoder();
        return encoder.encode(pw);

    }
}
