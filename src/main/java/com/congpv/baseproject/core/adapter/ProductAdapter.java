package com.congpv.baseproject.core.adapter;

import com.congpv.baseproject.core.domain.Product;

import com.congpv.baseproject.infrastructure.exception.ProductNotFoundException;
import java.util.List;

public interface ProductAdapter {
    List<Product> loadAllProducts();
    void insertNewProduct(Product product);
    Product loadProductDetails(Long id) throws ProductNotFoundException;
}
