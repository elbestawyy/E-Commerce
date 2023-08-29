package com.ecommerce.lavana.Controller;

import com.ecommerce.lavana.Entity.Category;
import com.ecommerce.lavana.service.CategoryService;
import com.ecommerce.lavana.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("http://localhost:4200")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/secure/id")
    public ResponseEntity<Category> getCategoryById (@RequestParam("category_id") Long id) throws Exception {
        Category category = categoryService.findCategoryById(id);
        if (category == null) {
            throw new Exception("Brand not found");
        }
        return ResponseEntity.ok(category);
    }

    @GetMapping("/pagination")
    public Page<Category> findCategoryWithPagination(@RequestParam("offset") int offset){
        return categoryService.findCategoriesWithPagination(offset);
    }

    @GetMapping("/pagination/sort")
    public Page<Category> findCategoryWithPaginationAndSort(@RequestParam("offset") int offset,
                                                                  @RequestParam("field")String field ){
        return categoryService.findCategoriesWithPaginationAndSorting(offset,field);
    }



                                     /**     ADMIN CONTROLLER     */
    @PostMapping("/secure/create")
    public String createCategory(@RequestBody Category category,
                                 @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        categoryService.createCategory(category);
        return "Success";
    }

    @PutMapping("/secure/update")
    public Category updateCategory (@RequestParam("category_id") Long id,
                                    @RequestBody Category category,
                                    @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        return categoryService.updateCategory(id,category);
    }

    @DeleteMapping("/secure/delete")
    public void removeCategory (@RequestParam("category_id") Long id,
                                @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        categoryService.deleteCategoryById(id);
    }
}
