package com.ecommerce.lavana.service;

import com.ecommerce.lavana.Entity.Product;
import com.ecommerce.lavana.Entity.WishList;

import java.util.List;

public interface WishListService {
    public void addToWishList(String user , Long productId);
    public List<Product> getAllWishListsByUser(String user);
    public void deleteWishListByUser(String user, Long productId)throws Exception;
}
