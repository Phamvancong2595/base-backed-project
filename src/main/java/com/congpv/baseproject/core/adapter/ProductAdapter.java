package com.congpv.baseproject.core.adapter;

import com.congpv.baseproject.application.response.PriceCheckResult;
import com.congpv.baseproject.core.domain.Product;

import com.congpv.baseproject.infrastructure.shared.exception.ProductNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.domain.Pageable;

public interface ProductAdapter {

  List<Product> loadAllProducts(Pageable paging);

  void insertNewProduct(Product product);

  Product loadProductDetails(Long id) throws ProductNotFoundException;

  CompletableFuture<PriceCheckResult> checkAsyncPrice(Product product) throws InterruptedException;

  PriceCheckResult checkPrice(Product product) throws InterruptedException;
}
