package com.ecommerce.lavana.DAO;

import com.ecommerce.lavana.Entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList,Long> {
    List<WishList> findWishListByuser(String user);

    WishList findByUserAndProductId(String user , Long productId);
}
