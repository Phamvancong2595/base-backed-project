package com.congpv.baseproject.application.controller;

import com.congpv.baseproject.application.request.NewProductRequest;
import com.congpv.baseproject.application.request.PriceCheckRequest;
import com.congpv.baseproject.application.response.FindProductResponse;
import com.congpv.baseproject.application.response.NewProductResponse;
import com.congpv.baseproject.application.response.PriceCheckResponse;
import com.congpv.baseproject.application.response.PriceCheckResult;
import com.congpv.baseproject.core.domain.Product;
import com.congpv.baseproject.core.service.ProductService;
import com.congpv.baseproject.infrastructure.shared.constants.AppConstants;
import com.congpv.baseproject.infrastructure.shared.exception.EmptyRequestException;
import com.congpv.baseproject.infrastructure.shared.exception.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product Controller")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping(
      value = "/add",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @Parameter(
      name = AppConstants.FIX_TOKEN_HEADER,
      required = true,
      in = ParameterIn.HEADER,
      example = "39489c18-7b74-11ec-90d6-0242ac120003")
  public NewProductResponse addNewProduct(@RequestBody NewProductRequest request) {
    productService.insertNewProduct(request);
    return new NewProductResponse("ok");
  }

  @GetMapping(value = "/get_details/{id}")
  @Parameter(
      name = AppConstants.FIX_TOKEN_HEADER,
      required = true,
      in = ParameterIn.HEADER,
      example = "39489c18-7b74-11ec-90d6-0242ac120003")
  public FindProductResponse getProductDetails(@PathVariable Long id)
      throws ProductNotFoundException {
    return FindProductResponse.builder()
        .product(productService.loadProductDetails(id))
        .success(true)
        .build();
  }

  @PostMapping(value = "/check-price-async", consumes = {
      MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  @Parameter(
      name = AppConstants.FIX_TOKEN_HEADER,
      required = true,
      in = ParameterIn.HEADER,
      example = "39489c18-7b74-11ec-90d6-0242ac120003")
  public PriceCheckResponse checkAsyncProductPrice(@RequestBody PriceCheckRequest request)
      throws EmptyRequestException, ExecutionException, InterruptedException {
    List<PriceCheckResult> results = productService.checkAsyncPrice(request);
    return PriceCheckResponse.builder().results(results).message("ok").success(true).build();
  }

  @PostMapping(
      value = "/check-price",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE}
  )
  @Parameter(
      name = AppConstants.FIX_TOKEN_HEADER,
      required = true,
      in = ParameterIn.HEADER,
      example = "39489c18-7b74-11ec-90d6-0242ac120003")
  public PriceCheckResponse checkProductPrice(@RequestBody PriceCheckRequest request)
      throws EmptyRequestException, InterruptedException {
    List<PriceCheckResult> results = productService.checkPrice(request);
    return PriceCheckResponse.builder().results(results).message("Ok").success(true).build();
  }

  @GetMapping(value = "/get-all")
  @Parameter(
      name = AppConstants.FIX_TOKEN_HEADER,
      required = true,
      in = ParameterIn.HEADER,
      example = "39489c18-7b74-11ec-90d6-0242ac120003")
  public ResponseEntity<List<Product>> getProductByPage(
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "20") Integer pageSize,
      @RequestParam(defaultValue = "id") String sortBy
  ) {
    List<Product> products = productService.loadAllProducts(pageNo, pageSize, sortBy);
    return new ResponseEntity<>(products, HttpStatus.OK);
  }
}
