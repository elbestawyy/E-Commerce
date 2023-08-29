package com.ecommerce.lavana.Controller;

import com.ecommerce.lavana.DTO.ProductDTO;
import com.ecommerce.lavana.Entity.Product;
import com.ecommerce.lavana.service.ProductService;
import com.ecommerce.lavana.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("http://localhost:4200")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Search By id , Brand , Subcategory , Category
    @GetMapping()
    public Product findProductById(@RequestParam("id") Long id)throws Exception{
        return productService.findProductById(id);
    }

    @GetMapping("/by-brand")
    public List<Product> getProductByBrandId(@RequestParam("brand_id") Long brandId) throws Exception {
       return productService.getProductByBrandId(brandId);
    }

    @GetMapping("/by-subcategory")
    public List<Product> getProductBySubcategoryId(@RequestParam("subcategory_id") Long subcategoryId) throws Exception {
        return productService.getProductBySubcategoryId(subcategoryId);
    }

    @GetMapping("/by-category")
    public List<Product> getProductByCategoryId(@RequestParam("category_id") Long categoryId) throws Exception {
        return productService.getProductByCategoryId(categoryId);
    }


    // Sorting and Pagination
    @GetMapping("/pagination")
    public Page<Product> findProductWithPagination(@RequestParam("offset") int offset){
        return productService.findProductsWithPagination(offset);
    }

    @GetMapping("/pagination/sort")
    public Page<Product> findProductWithPaginationAndSort(@RequestParam("offset") int offset,
                                                          @RequestParam("field")String field ){
        return productService.findProductsWithPaginationAndSorting(offset,field);
    }

    @PutMapping("/secure/discount")
    public void applyCouponToProduct(@RequestParam("product_id") Long productId,
                                     @RequestParam("coupon_code") String couponCode) throws Exception{
        productService.applyCouponToProduct(couponCode, productId);
    }

                                  /**     ADMIN CONTROLLER     */
    @PostMapping("/secure/add")
    public void addNewProduct(@RequestBody ProductDTO productDTO,
                              @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        productService.postProduct(productDTO);
    }

    @PutMapping("/secure/update")
    public Product updateProduct (@RequestParam("product_id") Long id,
                                    @RequestBody Product product,
                                  @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/secure/delete")
    public void removeProduct (@RequestParam("product_id") Long id,
                               @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        productService.deleteProductById(id);
    }
}
