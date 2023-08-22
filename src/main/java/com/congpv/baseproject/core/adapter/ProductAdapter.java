package com.congpv.baseproject.core.adapter;

import com.congpv.baseproject.application.response.PriceCheckResult;
import com.congpv.baseproject.core.domain.Product;

import com.congpv.baseproject.infrastructure.exception.ProductNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProductAdapter {

  List<Product> loadAllProducts();

  void insertNewProduct(Product product);

  Product loadProductDetails(Long id) throws ProductNotFoundException;

  CompletableFuture<PriceCheckResult> checkAsyncPrice(Product product) throws InterruptedException;

  PriceCheckResult checkPrice(Product product) throws InterruptedException;
}
