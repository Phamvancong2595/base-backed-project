package com.congpv.baseproject.application.controller;

import com.congpv.baseproject.application.request.NewProductRequest;
import com.congpv.baseproject.application.response.FindProductResponse;
import com.congpv.baseproject.application.response.NewProductResponse;
import com.congpv.baseproject.core.domain.Product;
import com.congpv.baseproject.core.service.ProductService;
import com.congpv.baseproject.infrastructure.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping(
      value = "/add",
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public NewProductResponse addNewProduct(@RequestBody NewProductRequest request) {
    productService.insertNewProduct(request);
    return new NewProductResponse("ok");
  }
  @GetMapping(value = "/get_details/{id}")
  public FindProductResponse getProductDetails(@PathVariable Long id) throws ProductNotFoundException {
    return FindProductResponse.builder()
        .product(productService.loadAllProductDetails(id))
        .status(true)
        .build();
  }
}
