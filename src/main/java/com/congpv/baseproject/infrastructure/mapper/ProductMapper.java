package com.congpv.baseproject.infrastructure.mapper;

import com.congpv.baseproject.core.domain.Product;
import com.congpv.baseproject.repository.mysql.entity.ProductEntity;
import com.congpv.baseproject.repository.mysql.vo.ProductDetailVO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {

    //@Mappings({})
    ProductEntity toEntity(Product product);

    //@Mappings({})
    Product toModel(ProductEntity entity);
    Product toModelV2(ProductDetailVO vo);
}
