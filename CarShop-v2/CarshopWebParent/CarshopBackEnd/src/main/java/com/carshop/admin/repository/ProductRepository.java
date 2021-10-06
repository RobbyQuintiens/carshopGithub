package com.carshop.admin.repository;

import com.carshop.common.entity.Brandstof;
import com.carshop.common.entity.Product;
import com.carshop.common.entity.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndProductStatus(Long id, ProductStatus status);
    List<Product> findByProductStatus(ProductStatus status);
}
