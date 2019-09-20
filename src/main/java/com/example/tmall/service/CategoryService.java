package com.example.tmall.service;

import com.example.tmall.pojo.Category;
import com.example.tmall.util.Page;

import java.util.List;

public interface CategoryService {
    List<Category> list();
    void add(Category category);
    void delete(int id);
    Category get(int id);
    void update(Category category);
}
