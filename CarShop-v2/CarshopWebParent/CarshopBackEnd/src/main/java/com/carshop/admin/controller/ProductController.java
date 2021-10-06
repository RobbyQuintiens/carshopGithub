package com.carshop.admin.controller;

import com.carshop.admin.dto.CreateModelResource;
import com.carshop.admin.dto.CreateProductResource;
import com.carshop.admin.dto.ModelDto;
import com.carshop.admin.dto.ProductDto;
import com.carshop.admin.exception.BrandNotFoundException;
import com.carshop.admin.exception.ProductNotFoundException;
import com.carshop.admin.helper.ModelDeserializer;
import com.carshop.admin.helper.ProductDeserializer;
import com.carshop.admin.service.BrandService;
import com.carshop.admin.service.ProductService;
import com.carshop.common.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/services/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<ProductDto> listAll() {
        return productService.listAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable(name = "id") Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> saveProduct(@RequestBody String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(CreateProductResource.class, new ProductDeserializer());
        mapper.registerModule(module);
        CreateProductResource createProductResource = mapper.readValue(jsonString, CreateProductResource.class);
        productService.saveProduct(createProductResource);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list/{status}")
    public ResponseEntity<List<ProductDto>> listByStatus(@PathVariable(name = "status") String status){
        ProductStatus productStatus = ProductStatus.valueOf(status.toUpperCase());
        System.out.println(productStatus);
        return new ResponseEntity<List<ProductDto>>(productService.listByStatus(productStatus), HttpStatus.OK);
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<Void> reserveProduct(@PathVariable(name = "id") Long id){
        productService.setProductStatusReserved(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/available")
    public ResponseEntity<Void> availableProduct(@PathVariable(name = "id") Long id){
        productService.setProductStatusAvailable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}/sold")
    public ResponseEntity<Void> soldProduct(@PathVariable(name = "id") Long id){
        productService.setProductStatusSold(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO uncomment after update ProductService

//    @PutMapping("/edit/{id}")
//    public ResponseEntity<Void> updateModel(@PathVariable(name = "id") Long id,
//                                            @RequestBody @Valid CreateProductResource productResource){
//        productService.updateProduct(id, productResource);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }


}
