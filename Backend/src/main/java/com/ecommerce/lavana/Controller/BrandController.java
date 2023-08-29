package com.ecommerce.lavana.Controller;

import com.ecommerce.lavana.Entity.Brand;
import com.ecommerce.lavana.service.BrandService;
import com.ecommerce.lavana.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin("http://localhost:4200")
public class BrandController {
    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/secure/id")
    public ResponseEntity<Brand> getBrandById (@RequestParam("brand_id") Long id) throws Exception {
        Brand brand = brandService.findBrandById(id);
        if (brand == null) {
            throw new Exception("Brand not found");
        }
        return ResponseEntity.ok(brand);
    }
    @GetMapping("/by-subcategory")
    public List<Brand> getBrandsBySubcategoryId(@RequestParam("subcategory_id") Long subcategoryId)throws Exception{
        return brandService.findBrandBySubcategoryId(subcategoryId);
    }
    @GetMapping("/by-category")
    public List<Brand> getBrandsByCategoryId(@RequestParam("category_id") Long categoryId)throws Exception{
        return brandService.findBrandByCategoryId(categoryId);
    }

    @GetMapping("/pagination")
    public Page<Brand> findBrandWithPagination(@RequestParam("offset") int offset){
        return brandService.findBrandsWithPagination(offset);
    }

    @GetMapping("/pagination/sort")
    public Page<Brand> findBrandWithPaginationAndSort(@RequestParam("offset") int offset,
                                                            @RequestParam("field")String field ){
        return brandService.findBrandsWithPaginationAndSorting(offset,field);
    }


                             /**     ADMIN CONTROLLER     */
    @PostMapping("/secure/create")
    public String createBrand(@RequestBody Brand brand,
                              @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        brandService.createBrand(brand);
        return "Success";
    }
    @PutMapping("/secure/update")
    public Brand updateBrand (@RequestParam("brand_id") Long id,
                              @RequestBody Brand brand,
                              @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        Brand brand1 = brandService.updateBrand(id,brand);
        if (brand1 == null) {
            throw new Exception("Brand not found");
        }
        return brand1;
    }

    @DeleteMapping("/secure/delete")
    public void removeBrand (@RequestParam("brand_id") Long id,
                             @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        brandService.deleteBrandById(id);
    }
}
