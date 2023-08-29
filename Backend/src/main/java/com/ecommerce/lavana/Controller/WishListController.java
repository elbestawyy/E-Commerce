package com.ecommerce.lavana.Controller;

import com.ecommerce.lavana.Entity.Product;
import com.ecommerce.lavana.service.WishListService;
import com.ecommerce.lavana.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin("http://localhost:4200")
public class WishListController {
    private WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PutMapping("/secure/add")
    public String addToWishList(@RequestHeader(value = "Authorization") String token,
                              @RequestParam("product_id") Long productId) throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        wishListService.addToWishList(user,productId);
        return "Successful";
    }

    @GetMapping("/secure/all")
    public List<Product> getWishListsByUser(@RequestHeader(value = "Authorization") String token)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        return wishListService.getAllWishListsByUser(user);
    }

    @DeleteMapping("/secure/delete")
    public void deleteWishListByUser(@RequestHeader(value = "Authorization") String token,
                                     @RequestParam("wishlist_id") Long wishListId) throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        wishListService.deleteWishListByUser(user,wishListId);
    }
}
