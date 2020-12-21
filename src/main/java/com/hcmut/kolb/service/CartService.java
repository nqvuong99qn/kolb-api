package com.hcmut.kolb.service;

import com.hcmut.kolb.entity.Cart;
import com.hcmut.kolb.entity.ProductInOrder;
import com.hcmut.kolb.entity.User;

import java.util.Collection;


public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
