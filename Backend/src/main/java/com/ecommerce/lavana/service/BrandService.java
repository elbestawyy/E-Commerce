package com.ecommerce.lavana.service;

import com.ecommerce.lavana.Entity.Brand;
import com.ecommerce.lavana.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BrandService {
    public Brand findBrandById(Long id)throws Exception;
    public List<Brand> findBrandBySubcategoryId(Long subcategoryId)throws Exception;
    public List<Brand> findBrandByCategoryId(Long categoryId)throws Exception;

    public Page<Brand> findBrandsWithPagination(int offset);
    public Page<Brand> findBrandsWithPaginationAndSorting(int offset,String field);

    public void createBrand(Brand brand);
    public Brand updateBrand (Long id,Brand brand)throws Exception;
    public void deleteBrandById(Long id)throws Exception;

}
