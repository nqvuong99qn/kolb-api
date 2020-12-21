package com.hcmut.kolb.service;

import com.hcmut.kolb.entity.ProductInOrder;
import com.hcmut.kolb.entity.User;


public interface ProductInOrderService {
    void update(String itemId, Integer quantity, User user);
    ProductInOrder findOne(String itemId, User user);
}
