package com.congpv.baseproject.repository.read_only;

import com.congpv.baseproject.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface RoProductRepository extends JpaRepository<ProductEntity, Long> {}
