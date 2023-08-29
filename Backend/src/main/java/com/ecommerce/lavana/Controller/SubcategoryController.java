package com.ecommerce.lavana.Controller;

import com.ecommerce.lavana.Entity.Subcategory;
import com.ecommerce.lavana.service.SubcategoryService;
import com.ecommerce.lavana.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategory")
@CrossOrigin("http//localhost:4200")
public class SubcategoryController {
    private SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }


    @GetMapping("/secure/id")
    public ResponseEntity<Subcategory> getSubcategoryById (@RequestParam("subcategory_id") Long id) throws Exception {
        Subcategory subCategory = subcategoryService.findSubcategoryById(id);
        if (subCategory == null) {
            throw new Exception("Brand not found");
        }
        return ResponseEntity.ok(subCategory);
    }

    @GetMapping("/by-category")
    public List<Subcategory> getSubcategoryByCategoryId(@RequestParam("category_id") Long categoryId)throws Exception{
        return subcategoryService.findSubcategoryByCategoryId(categoryId);
    }

    @GetMapping("/pagination")
    public Page<Subcategory> findSubcategoryWithPagination(@RequestParam("offset") int offset){
        return subcategoryService.findProductsWithPagination(offset);
    }

    @GetMapping("/pagination/sort")
    public Page<Subcategory> findSubcategoryWithPaginationAndSort(@RequestParam("offset") int offset,
                                                          @RequestParam("field")String field ){
        return subcategoryService.findProductsWithPaginationAndSorting(offset,field);
    }

                             /**     ADMIN CONTROLLER     */
    @PostMapping("/secure/create")
    public void createSubcategory(@RequestBody Subcategory subcategory,
                                  @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        subcategoryService.createSubcategory(subcategory);
    }
    @PutMapping("/secure/update")
    public Subcategory updateSubcategory (@RequestParam("subcategory_id") Long id,
                                          @RequestBody Subcategory subCategory,
                                          @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        return subcategoryService.updateSubcategory(id,subCategory);
    }

    @DeleteMapping("/secure/delete")
    public void removeSubcategory (@RequestParam("subcategory_id") Long id,
                                   @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        subcategoryService.deleteSubcategoryById(id);
    }
}
