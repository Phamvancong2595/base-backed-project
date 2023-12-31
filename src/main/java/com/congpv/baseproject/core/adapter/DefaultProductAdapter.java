package com.congpv.baseproject.core.adapter;

import com.congpv.baseproject.application.response.PriceCheckResult;
import com.congpv.baseproject.core.domain.Product;
import com.congpv.baseproject.infrastructure.shared.exception.ProductNotFoundException;
import com.congpv.baseproject.infrastructure.shared.mapper.ProductMapper;
import com.congpv.baseproject.infrastructure.shared.constants.AppConstants;
import com.congpv.baseproject.infrastructure.repository.mongo.entity.DemoLog;
import com.congpv.baseproject.infrastructure.repository.mongo.primary.DemoLogRepository;
import com.congpv.baseproject.infrastructure.repository.mysql.entity.ProductEntity;
import com.congpv.baseproject.infrastructure.repository.mysql.primary.ProductRepository;
import com.congpv.baseproject.infrastructure.repository.mysql.read_only.RoProductRepository;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("DefaultProductAdapter")
@RequiredArgsConstructor
public class DefaultProductAdapter implements ProductAdapter {

  private final ProductRepository productRepository;
  private final RoProductRepository roProductRepository;
  private final DemoLogRepository logRepository;
  private final ProductMapper mapper;

  @Override
  public List<Product> loadAllProducts(Pageable paging) {
    DemoLog requestLog = DemoLog.builder().requestId(MDC.get(AppConstants.REQUEST_ID_KEY)).build();
    logRepository.insert(requestLog);
    return roProductRepository.findAll(paging).stream().map(mapper::toModel)
        .collect(Collectors.toList());
  }

  @Override
  public void insertNewProduct(Product product) {
    this.productRepository.save(this.mapper.toEntity(product));
  }

  @Override
  @Cacheable(cacheNames = "productDetails", unless = "#result == null")
  public Product loadProductDetails(Long id) throws ProductNotFoundException {
    Product product = mapper.toModelV2(roProductRepository.getProductDetailsById(id));
    if (Objects.isNull(product)) {
      throw new ProductNotFoundException("Product Not Found");
    }
    return product;
  }

  @Async("threadPoolTaskExecutor")
  @Override
  public CompletableFuture<PriceCheckResult> checkAsyncPrice(Product product)
      throws InterruptedException {
    Optional<ProductEntity> productEntity =
        productRepository.findById(product.getId());
    if (productEntity.isEmpty() || !Objects.equals(productEntity.get().getPrice(),
        product.getPrice())) {
      return CompletableFuture.completedFuture(
          PriceCheckResult.builder().id(product.getId()).status(false).build());
    }
    TimeUnit.SECONDS.sleep(1);
    return CompletableFuture.completedFuture(
        PriceCheckResult.builder().id(product.getId()).status(true).build());
  }

  @Override
  public PriceCheckResult checkPrice(Product product) throws InterruptedException {
    Optional<ProductEntity> productEntity = productRepository.findById(product.getId());
    if (productEntity.isEmpty() || !Objects.equals(productEntity.get().getPrice(),
        product.getPrice())) {
      return PriceCheckResult.builder().id(product.getId()).status(false).build();
    }
    TimeUnit.SECONDS.sleep(1);
    return PriceCheckResult.builder().id(product.getId()).status(true).build();
  }
}
