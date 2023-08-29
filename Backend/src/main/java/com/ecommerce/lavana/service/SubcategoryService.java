package com.ecommerce.lavana.service;

import com.ecommerce.lavana.Entity.Product;
import com.ecommerce.lavana.Entity.Subcategory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubcategoryService {
    public Subcategory findSubcategoryById(Long id)throws Exception;
    public List<Subcategory> findSubcategoryByCategoryId(Long id)throws Exception;

    public Page<Subcategory> findProductsWithPagination(int offset);
    public Page<Subcategory> findProductsWithPaginationAndSorting(int offset,String field);

    public void createSubcategory(Subcategory subCategory);
    public Subcategory updateSubcategory (Long id, Subcategory subCategory)throws Exception;
    public void deleteSubcategoryById(Long id)throws Exception;
}
