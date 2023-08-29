package com.ecommerce.lavana.DAO;

import com.ecommerce.lavana.Entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
    List<Subcategory> findSubcategoryByCategoryId(Long id);
}
