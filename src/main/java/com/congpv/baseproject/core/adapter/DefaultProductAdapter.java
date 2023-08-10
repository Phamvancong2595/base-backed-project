package com.congpv.baseproject.core.adapter;

import com.congpv.baseproject.core.domain.Product;
import com.congpv.baseproject.infrastructure.mapper.ProductMapper;
import com.congpv.baseproject.repository.primary.ProductRepository;
import com.congpv.baseproject.repository.read_only.RoProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component("DefaultProductAdapter")
@RequiredArgsConstructor
public class DefaultProductAdapter implements ProductAdapter {
    private final ProductRepository productRepository;
    private final RoProductRepository roProductRepository;
    private final ProductMapper mapper;
    @Override
    public List<Product> loadAllProducts() {
        return roProductRepository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
    }

    @Override
    public void insertNewProduct(Product product) {
        this.productRepository.save(this.mapper.toEntity(product));
    }

    @Override
    public Product loadProductDetails(Long id) {
        return mapper.toModelV2(roProductRepository.getProductDetailsById(id));
    }
}
