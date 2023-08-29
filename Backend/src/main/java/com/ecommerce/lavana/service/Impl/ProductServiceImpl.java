package com.ecommerce.lavana.service.Impl;

import com.ecommerce.lavana.DAO.CouponRepository;
import com.ecommerce.lavana.DAO.ProductRepository;
import com.ecommerce.lavana.Entity.Coupon;
import com.ecommerce.lavana.Entity.Product;
import com.ecommerce.lavana.DTO.ProductDTO;
import com.ecommerce.lavana.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CouponRepository couponRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CouponRepository couponRepository) {
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
    }


    @Override
    public Product findProductById(Long id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()){
            throw new Exception("Product not found");
        }
        return product.get();
    }

    // search by brand & subcategory & category
    @Override
    public List<Product> getProductByBrandId(Long brandId) throws Exception{
        List<Product> products =productRepository.findByBrandId(brandId);
        if (products == null) {
            throw new Exception("No products found for this brand");
        }
        return products;
    }

    @Override
    public List<Product> getProductBySubcategoryId(Long subcategoryId)throws Exception {
        List<Product> products =productRepository.findBySubcategoryId(subcategoryId);
        if (products == null) {
            throw new Exception("No products found for this subcategory");
        }
        return products;
    }

    @Override
    public List<Product> getProductByCategoryId(Long categoryId)throws Exception {
        List<Product> products =productRepository.findByCategoryId(categoryId);
        if (products == null) {
            throw new Exception("No products found for this category");
        }
        return products;
    }


    // Sorting and Pagination
    @Override
    public Page<Product> findProductsWithPagination(int offset) {
         final int pageSize = 10;
         return productRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<Product> findProductsWithPaginationAndSorting(int offset,  String field) {
        final int pageSize = 10;
        return productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }


                              /** Apply Discount to Product */
    @Override
    public void applyCouponToProduct(String couponCode,Long productId)throws Exception{
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()){
            throw new Exception("Product not found");
        }
        Optional<Coupon> coupon = couponRepository.findCouponByCode(couponCode);
        if (coupon.isEmpty() || Date.valueOf(LocalDate.now()).after(coupon.get().getExpiredDate())){
            throw new Exception("no discount for this coupon now");
        }
        double discountPercentage = coupon.get().getDiscountPercentage();
        double discountPrice = product.get().getPrice() * discountPercentage;
        if (discountPercentage > 1) {
            discountPrice = product.get().getPrice() * (discountPercentage/100);
        }
        double priceAfterDiscount = product.get().getPrice() - discountPrice;
        product.get().setPriceAfterDiscount(priceAfterDiscount);
        productRepository.save(product.get());
    }


                             /**              ADMIN SERVICE            */
    @Override
    public void postProduct(ProductDTO productDTO) {
        Product product =new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        product.setPriceAfterDiscount(productDTO.getPriceAfterDiscount());
        product.setColour(productDTO.getColour());
        product.setImgCover(productDTO.getImgCover());
        product.setBrand(productDTO.getBrand());
        productRepository.save(product);
    }
    @Override
    public Product updateProduct(Long id, Product product) throws Exception {
        Optional<Product> validateProduct = productRepository.findById(id);
        if (!validateProduct.isPresent()) {
            throw new Exception("subcategory not found");
        }
        validateProduct.get().setTitle(product.getTitle());
        validateProduct.get().setImgCover(product.getImgCover());
        validateProduct.get().setQuantity(product.getQuantity());
        validateProduct.get().setColour(product.getColour());
        validateProduct.get().setBrand(product.getBrand());
        validateProduct.get().setDescription(product.getDescription());
        validateProduct.get().setPrice(product.getPrice());
        validateProduct.get().setPriceAfterDiscount(product.getPriceAfterDiscount());
        return validateProduct.get();
    }

    @Override
    public void deleteProductById(Long id) throws Exception {
        Optional<Product> validateProduct = productRepository.findById(id);
        if (!validateProduct.isPresent()) {
            throw new Exception("Subcategory not found");
        }
        productRepository.deleteById(id);
    }
}
