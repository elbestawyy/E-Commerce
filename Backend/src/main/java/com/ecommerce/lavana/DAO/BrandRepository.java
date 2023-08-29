package com.ecommerce.lavana.DAO;

import com.ecommerce.lavana.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BrandRepository extends JpaRepository<Brand,Long> {
    List<Brand> findBrandsBySubcategoryId(Long id);

    @Query("select b from Brand b join b.subcategory s join s.category c where c.id = :categoryId")
    List<Brand> findBrandsByCategory (@Param("categoryId") Long id);
}
