package com.ecommerce.lavana.DAO;

import com.ecommerce.lavana.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findCartByUser(String user);
}
