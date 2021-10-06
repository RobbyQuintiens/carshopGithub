package com.carshop.admin.service;

import com.carshop.admin.dto.CreateProductResource;
import com.carshop.admin.dto.ModelDto;
import com.carshop.admin.dto.ProductDto;
import com.carshop.admin.exception.ProductNotFoundException;
import com.carshop.admin.repository.ProductRepository;
import com.carshop.common.entity.Conditie;
import com.carshop.common.entity.Model;
import com.carshop.common.entity.Product;
import com.carshop.common.entity.Transmissie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Model model;

    @BeforeEach
    public void init(){
        model = new Model();
        model.setName("testModel");
        product = new Product(Conditie.NIEUW, model,
        2021, "Blauw", 5, Transmissie.AUTOMATISCH, 0,
        50000, null);
    }

    @Test
    public void createProduct(){
        when(productRepository.save(any(Product.class))).thenReturn(product);
        CreateProductResource productResource = new CreateProductResource(product.getConditie(), product.getModel(),
                product.getBouwjaar(), product.getKoetswerkkleur(), product.getAantalDeuren(), product.getTransmissie(),
                product.getAantalKm(), product.getPrijs(), null);
        Product productDto = productService.saveProduct(productResource);
        assertEquals(productDto.getModel().getName(), productResource.getModel().getName());
    }

    @Test
    public void getProductReturnsExceptionWhenIdNotFound(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.getProduct(product.getId()));
    }

    //TODO test getProduct

    @Test
    public void getProductReturnsProductWhenIdFound(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        ProductDto productDto = productService.getProduct(product.getId());
        System.out.println(productDto.getPrijs());
        assertEquals(product.getPrijs(), productDto.getPrijs());
    }

    @Test
    public void deleteProductReturnsOk(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productService.deleteProduct(product.getId());
        Mockito.verify(productRepository).delete(product);
    }

    //TODO test getProduct

    @Test
    public void listAllProductsReturnsList(){
        when(productRepository.findAll()).thenReturn(List.of(product));
        productService.listAll();
        Mockito.verify(productRepository).findAll();
    }

    //TODO test updateProduct
}
