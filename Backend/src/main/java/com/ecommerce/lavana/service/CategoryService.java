package com.ecommerce.lavana.service;

import com.ecommerce.lavana.Entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    public Category findCategoryById(Long id)throws Exception;

    public Page<Category> findCategoriesWithPagination(int offset);
    public Page<Category> findCategoriesWithPaginationAndSorting(int offset,String field);

    public void createCategory(Category category);
    public Category updateCategory (Long id, Category category)throws Exception;
    public void deleteCategoryById(Long id)throws Exception;
}
