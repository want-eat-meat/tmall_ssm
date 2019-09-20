package com.example.tmall.service;

import com.example.tmall.pojo.Product;
import com.example.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    void init(Product p );
    void update(PropertyValue propertyValue);
    PropertyValue get(int ptid, int pid);
    List<PropertyValue> list(int pid);
}
