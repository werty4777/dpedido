package com.aemw.pedidos.Cart.Domain;


import com.aemw.pedidos.Cart.Infrastructure.api.Models.ICartDetails;
import com.aemw.pedidos.Cart.Infrastructure.api.Models.detailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalCart extends JpaRepository<cartDetail,Integer> {

    @Query(value="select product_name as product_name,pt.id_product as id_product,image,quantity,unite_price as unite_price from cart_detail dt inner join cart  ct on(dt.idcart=ct.id_cart) inner join product pt on (dt.Id_Product=pt.id_product) where ct.idcustomer=?;" ,nativeQuery=true)
    List<ICartDetails> getDetails(int a);




    List<cartDetail> findByIdcart(cart cart);



    @Query(value="select ctd.id_detail, quantity, idcart, id_product from cart_detail ctd inner join cart ct on(ctd.idcart=ct.id_cart) inner join customer cm on (cm.id_customer=ct.idcustomer) where cm.id_customer=? and ctd.id_product=?;",nativeQuery=true)
    List<detailModel> getCart(int a, int b);
}
