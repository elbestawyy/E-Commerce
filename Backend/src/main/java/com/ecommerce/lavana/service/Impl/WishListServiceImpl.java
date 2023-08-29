package com.ecommerce.lavana.service.Impl;

import com.ecommerce.lavana.DAO.ProductRepository;
import com.ecommerce.lavana.DAO.WishListRepository;
import com.ecommerce.lavana.Entity.Customer;
import com.ecommerce.lavana.Entity.Product;
import com.ecommerce.lavana.Entity.WishList;
import com.ecommerce.lavana.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@Transactional
public class WishListServiceImpl implements WishListService {
    private WishListRepository wishListRepository;
    private ProductRepository productRepository;

    @Autowired
    public WishListServiceImpl(WishListRepository wishListRepository, ProductRepository productRepository) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addToWishList(String user , Long productId){
        Optional<Product> product = productRepository.findById(productId);
        WishList wishList = new WishList(product.get(),user);
        wishListRepository.save(wishList);
    }

    @Override
    public List<Product> getAllWishListsByUser(String user){
        List<WishList> wishLists = wishListRepository.findWishListByuser(user);
        List<Product> products = new ArrayList<>();
        for (WishList wishList : wishLists) {
            products.add(wishList.getProduct());
        }
        return products ;
    }

    @Override
    public void deleteWishListByUser(String user, Long productId)throws Exception {
        WishList wishList = wishListRepository.findByUserAndProductId(user, productId);
        if (productId <= 0) {
            throw new Exception("product not found");
        }
        wishListRepository.deleteById(wishList.getId());
    }
}
