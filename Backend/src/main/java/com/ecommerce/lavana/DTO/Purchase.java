package com.ecommerce.lavana.DTO;

import com.ecommerce.lavana.Entity.Address;
import com.ecommerce.lavana.Entity.Customer;
import com.ecommerce.lavana.Entity.Order;
import com.ecommerce.lavana.Entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
