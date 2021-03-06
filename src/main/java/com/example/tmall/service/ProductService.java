package com.example.tmall.service;

import com.example.tmall.pojo.Category;
import com.example.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    void delete(int id);
    void update(Product product);
    Product get(int id);
    List list(int cid);
    void setFirstProductImage(Product p);
    void fill(List<Category> cs);
    void fill(Category category);
    void fillByRow(List<Category> cs);
    void setSaleAndReviewNumber(Product p);
    void setSaleAndReviewNumber(List<Product> ps);
    List<Product> search(String keyword);
}
