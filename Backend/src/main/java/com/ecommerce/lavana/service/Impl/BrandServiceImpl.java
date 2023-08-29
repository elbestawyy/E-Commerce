package com.ecommerce.lavana.service.Impl;

import com.ecommerce.lavana.DAO.BrandRepository;
import com.ecommerce.lavana.Entity.Brand;
import com.ecommerce.lavana.service.BrandService;
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
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findBrandById(Long id) throws Exception{
        Optional<Brand> brand = brandRepository.findById(id);
        if (!brand.isPresent()) {
            throw new RuntimeException("Brand not found");
        }
        return brand.get();
    }

    @Override
    public List<Brand> findBrandBySubcategoryId(Long subcategoryId) throws Exception {
        if (subcategoryId <= 0) {
            throw new Exception("Brand not Found");
        }
        return brandRepository.findBrandsBySubcategoryId(subcategoryId);
    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId)throws Exception {
        if (categoryId <= 0) {
            throw new Exception("Brand not Found");
        }
        return brandRepository.findBrandsByCategory(categoryId);
    }


    @Override
    public Page<Brand> findBrandsWithPagination(int offset) {
        final int pageSize = 10;
        return brandRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<Brand> findBrandsWithPaginationAndSorting(int offset,String field) {
        final int pageSize = 10;
        return brandRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }


                      /**              ADMIN SERVICE            */
    @Override
    public void createBrand(Brand brand){
        brandRepository.save(brand);
    }
    @Override
    public Brand updateBrand(Long id, Brand brand)throws Exception {
        Optional<Brand> validateBrand = brandRepository.findById(id);
        if (!validateBrand.isPresent()) {
            throw new Exception("Brand not found");
        }
        validateBrand.get().setTitle(brand.getTitle());
        validateBrand.get().setImg(brand.getImg());
        validateBrand.get().setSubcategory(brand.getSubcategory());
        return validateBrand.get();
    }

    @Override
    public void deleteBrandById(Long id) throws Exception {
        Optional<Brand> validateBrand = brandRepository.findById(id);
        if (!validateBrand.isPresent()) {
            throw new Exception("Brand not found");
        }
        brandRepository.deleteById(id);
    }
}
