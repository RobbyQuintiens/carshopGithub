package com.carshop.admin.service;

import com.carshop.admin.dto.CreateProductResource;
import com.carshop.admin.dto.ProductDto;
import com.carshop.admin.exception.ProductNotFoundException;
import com.carshop.admin.exception.ProductStatusException;
import com.carshop.admin.repository.*;
import com.carshop.common.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    public static final int PRODUCTS_PER_PAGE = 10;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandstofRepository brandstofRepository;

    public List<ProductDto> listAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }


    public Product saveProduct(CreateProductResource createProductResource) {
        Product product = new Product(createProductResource.getConditie(),
                createProductResource.getModel(), createProductResource.getBouwjaar(),
                createProductResource.getKoetswerkkleur(), createProductResource.getAantalDeuren(),
                createProductResource.getTransmissie(), createProductResource.getAantalKm(),
                createProductResource.getPrijs(), createProductResource.getAfbeelding());
        
        return productRepository.save(product);
    }

    public ProductDto getProduct(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Could not find product with id [" + id + "]"));
        return new ProductDto(foundProduct);
    }

    public void deleteProduct(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Could not find and delete product with id [" + id + "]"));
        productRepository.delete(foundProduct);
    }

    public List<ProductDto> listByStatus(ProductStatus status) {
        List<ProductDto> productList = productRepository.findByProductStatus(status)
                .stream().map(ProductDto::new).collect(Collectors.toList());
        if (productList.isEmpty()) {
            throw new ProductStatusException("No products found with status " + status.name());
        }
        return productList;
    }

    //TODO Add updateProduct

    @Transactional
    public void setProductStatusAvailable(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Could not find and delete product with id [" + id + "]"));
        if (foundProduct.getProductStatus() == ProductStatus.AVAILABLE) {
            throw new ProductStatusException("Product is already available");
        } else {
            foundProduct.setProductStatus(ProductStatus.AVAILABLE);
            foundProduct.setLockedAt(null);
        }
    }

    @Transactional
    public void setProductStatusReserved(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Could not find and delete product with id [" + id + "]"));
        if (foundProduct.getProductStatus() == ProductStatus.RESERVED) {
            throw new ProductStatusException("Product is already reserved");
        } else {
            foundProduct.setProductStatus(ProductStatus.RESERVED);
            foundProduct.setLockedAt(LocalDateTime.now());
        }
    }

    @Transactional
    public void setProductStatusSold(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Could not find and delete product with id [" + id + "]"));
        if (foundProduct.getProductStatus() == ProductStatus.SOLD) {
            throw new ProductStatusException("Product is already sold");
        } else {
            foundProduct.setProductStatus(ProductStatus.SOLD);
            foundProduct.setLockedAt(null);
            foundProduct.setConfirmedAt(LocalDateTime.now());
        }
    }

    //TODO Add setProductStatus Sold
}
