package com.congpv.baseproject.core.service;


import com.congpv.baseproject.application.request.NewProductRequest;
import com.congpv.baseproject.application.request.PriceCheckRequest;
import com.congpv.baseproject.application.response.PriceCheckResult;
import com.congpv.baseproject.core.domain.Product;
import com.congpv.baseproject.infrastructure.shared.exception.EmptyRequestException;
import com.congpv.baseproject.infrastructure.shared.exception.ProductNotFoundException;
import java.util.concurrent.ExecutionException;

import java.util.List;

public interface ProductService {

  void insertNewProduct(NewProductRequest request);

  List<Product> loadAllProducts(Integer pageNo, Integer pageSize, String sortBy);

  Product loadProductDetails(Long id) throws ProductNotFoundException;

  List<PriceCheckResult> checkAsyncPrice(PriceCheckRequest request)
      throws EmptyRequestException, ExecutionException, InterruptedException;

  List<PriceCheckResult> checkPrice(PriceCheckRequest request)
      throws EmptyRequestException, InterruptedException;
}