package com.example.tmall.service;

import com.example.tmall.pojo.Order;
import com.example.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order order);
    float add(Order order, List<OrderItem> ois);
    void delete(int id);
    void update(Order order);
    Order get(int id);
    List list();
    List list(int uid, String exculdedStatus);
}
