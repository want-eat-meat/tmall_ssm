package com.example.tmall.service.impl;

import com.example.tmall.mapper.ProductImageMapper;
import com.example.tmall.pojo.ProductImage;
import com.example.tmall.pojo.ProductImageExample;
import com.example.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService{

    @Autowired
    ProductImageMapper productImagemapper;
    @Override
    public void add(ProductImage productImage) {
        productImagemapper.insert(productImage);
    }

    @Override
    public void delete(int id) {
        productImagemapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productImage) {
        productImagemapper.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public ProductImage get(int id) {
        return productImagemapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid, String type) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andPidEqualTo(pid)
                .andTypeEqualTo(type);
        example.setOrderByClause("id desc");
        return productImagemapper.selectByExample(example);
    }
}
