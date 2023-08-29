package com.ecommerce.lavana.DAO;

import com.ecommerce.lavana.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByBrandId(Long brandId);

    @Query("select p from Product p join p.brand b join b.subcategory s where s.id = :subcategoryId")
    List<Product> findBySubcategoryId(@Param("subcategoryId") Long subcategoryId);

    @Query("select p from Product p join p.brand b join b.subcategory s join s.category c where c.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long subcategoryId);
}
