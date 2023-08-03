package com.congpv.baseproject.infrastructure.mapper;

import com.congpv.baseproject.core.domain.Product;
import com.congpv.baseproject.repository.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {

    @Mappings({})
    ProductEntity toEntity(Product product);

    @Mappings({})
    Product toModel(ProductEntity entity);
}
