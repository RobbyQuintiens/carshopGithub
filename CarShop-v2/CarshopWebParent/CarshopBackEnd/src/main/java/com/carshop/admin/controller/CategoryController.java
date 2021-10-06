package com.carshop.admin.controller;

import com.carshop.admin.dto.CategoryDto;
import com.carshop.admin.dto.CreateCategoryResource;
import com.carshop.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/services/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> listAll() {
        return categoryService.listAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable("id") Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createBrand(@RequestBody @Valid CreateCategoryResource categoryResource) {
        categoryService.createCategory(categoryResource);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

