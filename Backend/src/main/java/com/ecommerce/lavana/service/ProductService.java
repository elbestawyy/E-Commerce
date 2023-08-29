package com.ecommerce.lavana.service;


import com.ecommerce.lavana.DTO.ProductDTO;
import com.ecommerce.lavana.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public Product findProductById(Long Id)throws Exception;

    // search by brand & subcategory & category
    List<Product> getProductByBrandId(Long brandId)throws Exception;
    List<Product> getProductBySubcategoryId(Long subcategoryId)throws Exception;
    List<Product> getProductByCategoryId(Long categoryId)throws Exception;


    // Sorting and Pagination
    public Page<Product> findProductsWithPagination(int pageSize);
    public Page<Product> findProductsWithPaginationAndSorting(int pageSize,String field);

    public void postProduct(ProductDTO productDTO);
    public Product updateProduct (Long id, Product product)throws Exception;
    public void deleteProductById(Long id)throws Exception;
    public void applyCouponToProduct(String couponCode,Long productId)throws Exception;

}
