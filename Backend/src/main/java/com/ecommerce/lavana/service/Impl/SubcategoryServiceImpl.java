package com.ecommerce.lavana.service.Impl;

import com.ecommerce.lavana.DAO.SubcategoryRepository;
import com.ecommerce.lavana.Entity.Subcategory;
import com.ecommerce.lavana.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {
    private SubcategoryRepository subCategoryRepository;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public Subcategory findSubcategoryById(Long id) throws Exception {
        Optional<Subcategory> subCategory = subCategoryRepository.findById(id);
        if (!subCategory.isPresent()) {
            throw new RuntimeException("subcategory not found");
        }
        return subCategory.get();
    }

    @Override
    public List<Subcategory> findSubcategoryByCategoryId(Long id) throws Exception{
        if (id <= 0) {
            throw new Exception("subcategory not found");
        }
        return subCategoryRepository.findSubcategoryByCategoryId(id);
    }


    // Sorting and Pagination
    @Override
    public Page<Subcategory> findProductsWithPagination(int offset) {
        final int pageSize = 10;
        return subCategoryRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<Subcategory> findProductsWithPaginationAndSorting(int offset, String field) {
        final int pageSize = 10;
        return subCategoryRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }


                     /**              ADMIN SERVICE            */
    @Override
    public void createSubcategory(Subcategory subCategory) {
        subCategoryRepository.save(subCategory);
    }
    @Override
    public Subcategory updateSubcategory(Long id, Subcategory subCategory) throws Exception {
        Optional<Subcategory> validateSubCategory = subCategoryRepository.findById(id);
        if (!validateSubCategory.isPresent()) {
            throw new Exception("subcategory not found");
        }
        validateSubCategory.get().setTitle(subCategory.getTitle());
        validateSubCategory.get().setImg(subCategory.getImg());
        validateSubCategory.get().setCategory(subCategory.getCategory());
        return validateSubCategory.get();
    }
    @Override
    public void deleteSubcategoryById(Long id) throws Exception {
        Optional<Subcategory> validateBrand = subCategoryRepository.findById(id);
        if (!validateBrand.isPresent()) {
            throw new Exception("Subcategory not found");
        }
        subCategoryRepository.deleteById(id);
    }
}
