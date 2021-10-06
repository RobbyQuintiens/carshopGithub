package com.carshop.admin.controller;

import com.carshop.admin.dto.BrandDto;
import com.carshop.admin.dto.CreateBrandResource;
import com.carshop.admin.dto.ModelDto;
import com.carshop.admin.service.BrandService;
import com.carshop.common.entity.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/services/brands")
@CrossOrigin(origins = "http://localhost:4200")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<BrandDto> listAll() {
        return brandService.listAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createBrand(@RequestBody @Valid CreateBrandResource brandResource) {
        brandService.createBrand(brandResource);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BrandDto getBrand(@PathVariable("id") Long id) {
        return brandService.getBrand(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable(name = "id") Long id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public  ResponseEntity<Void> updateBrand(@PathVariable(name = "id") Long id,
                                             @RequestBody @Valid CreateBrandResource brandResource) {
        brandService.updateBrand(id, brandResource);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public List<ModelDto> getModelsByBrand(@PathVariable(name = "id") Long id){
        return brandService.findModels(id);
    }

}
