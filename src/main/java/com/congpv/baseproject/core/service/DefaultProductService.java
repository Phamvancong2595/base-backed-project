package com.congpv.baseproject.core.service;

import com.congpv.baseproject.application.request.NewProductRequest;
import com.congpv.baseproject.application.request.PriceCheckRequest;
import com.congpv.baseproject.application.response.PriceCheckResult;
import com.congpv.baseproject.core.adapter.ProductAdapter;
import com.congpv.baseproject.core.domain.Product;
import com.congpv.baseproject.infrastructure.shared.exception.EmptyRequestException;
import com.congpv.baseproject.infrastructure.shared.exception.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component("ProductService")
@RequiredArgsConstructor
@Slf4j
public class DefaultProductService implements ProductService {

  private final ProductAdapter productAdapter;


  @Override
  public void insertNewProduct(NewProductRequest request) {
    log.info("> ProductService.insertNewProduct {}", request);
    Product product = Product.builder().name(request.getName()).price(request.getPrice()).build();
    this.productAdapter.insertNewProduct(product);
  }

  @Override
  public List<Product> loadAllProducts(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    return this.productAdapter.loadAllProducts(paging);
  }

  @Override
  public Product loadProductDetails(Long id) throws ProductNotFoundException {
    log.info("> ProductService.loadProductDetails id = {}", id);
    return this.productAdapter.loadProductDetails(id);
  }

  @Override
  public List<PriceCheckResult> checkAsyncPrice(PriceCheckRequest request)
      throws EmptyRequestException, ExecutionException, InterruptedException {
    log.info("> ProductService.checkAsyncPrice {}", request);
    if (Objects.isNull(request.getProducts()) || request.getProducts().isEmpty()) {
      throw new EmptyRequestException();
    }
    List<CompletableFuture<PriceCheckResult>> futures = new ArrayList<>(
        request.getProducts().size());
    for (Product p : request.getProducts()) {
      CompletableFuture<PriceCheckResult> future = this.productAdapter.checkAsyncPrice(p);
      futures.add(future);
    }
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
    List<PriceCheckResult> results = new ArrayList<>();
    for (CompletableFuture<PriceCheckResult> future : futures) {
      results.add(future.get());
    }
    return results;
  }

  @Override
  public List<PriceCheckResult> checkPrice(PriceCheckRequest request)
      throws EmptyRequestException, InterruptedException {
    log.info("> ProductService.checkPrice {}", request);
    if (Objects.isNull(request.getProducts()) || request.getProducts().isEmpty()) {
      throw new EmptyRequestException();
    }
    List<PriceCheckResult> results = new ArrayList<>();
    for (Product p : request.getProducts()) {
      results.add(this.productAdapter.checkPrice(p));
    }
    return results;
  }
}
