package com.ecommerce.lavana.Controller;

import com.ecommerce.lavana.DTO.CartDTO;
import com.ecommerce.lavana.DTO.CartItemDTO;
import com.ecommerce.lavana.service.CartService;
import com.ecommerce.lavana.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/secure")
@CrossOrigin("http://localhost:4200")
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public void addToCart(@RequestHeader(value = "Authorization") String token,
                          @RequestBody CartItemDTO cartItemDto)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        cartService.addToCart(user,cartItemDto);
    }

    @GetMapping("/all")
    public CartDTO getCartItem(@RequestHeader(value = "Authorization") String token)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        return cartService.listCartItems(user);
    }

    @DeleteMapping("/delete")
    public void deleteCartItem(@RequestHeader(value = "Authorization") String token,
                               @RequestParam Long cartItemId)throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        cartService.deleteCartItem(user,cartItemId);
    }
}
