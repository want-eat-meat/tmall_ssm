package com.example.tmall.service.impl;

import com.example.tmall.mapper.ProductMapper;
import com.example.tmall.mapper.PropertyMapper;
import com.example.tmall.pojo.Category;
import com.example.tmall.pojo.Product;
import com.example.tmall.pojo.ProductExample;
import com.example.tmall.pojo.ProductImage;
import com.example.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;
    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product get(int id) {
        Product p =  productMapper.selectByPrimaryKey(id);
        p.setCategory(categoryService.get(p.getCid()));
        setFirstProductImage(p);
        return p;
    }

    @Override
    public List list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List<Product> result = productMapper.selectByExample(example);
        for(Product p : result) {
            p.setCategory(categoryService.get(p.getCid()));
        }
        setFirstProductImage(result);
        return result;
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.type_single);
        if(!pis.isEmpty()){
            ProductImage productImage = pis.get(0);
            p.setFirstProductImage(productImage);
        }
    }

    @Override
    public void fill(List<Category> cs) {
        for(Category c : cs){
            fill(c);
        }
    }

    @Override
    public void fill(Category category) {
        List<Product> ps = list(category.getId());
        category.setProducts(ps);
    }

    @Override
    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for(Category c : cs){
            List<Product> products = c.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for(int i=0; i< products.size(); i+=productNumberEachRow){
                int size = i+productNumberEachRow;
                size = size>products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    public void setFirstProductImage(List<Product> ps){
        for(Product p : ps){
            setFirstProductImage(p);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product p) {
        int saleCount = orderItemService.getSaleCount(p.getId());
        p.setSaleCount(saleCount);

        int reviewCount = reviewService.getCount(p.getId());
        p.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {
        for (Product p : ps) {
            setSaleAndReviewNumber(p);
        }
    }

    @Override
    public List<Product> search(String keyword) {
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike("%" + keyword + "%");
        example.setOrderByClause("id desc");
        List<Product> result = productMapper.selectByExample(example);
        setFirstProductImage(result);
        for(Product p : result) {
            p.setCategory(categoryService.get(p.getCid()));
        }
        return result;
    }
}
