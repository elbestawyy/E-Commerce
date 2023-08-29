package com.ecommerce.lavana.DAO;

import com.ecommerce.lavana.Entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;



public interface ReviewRepository extends JpaRepository<Review,Long> {

    Page<Review> findReviewByProductId(@RequestParam("product_id") Long productId , Pageable pageable);
    Review findByUserAndProductId(String user,Long productId);
}
