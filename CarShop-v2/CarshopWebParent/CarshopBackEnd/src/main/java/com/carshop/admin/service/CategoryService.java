package com.carshop.admin.service;

import com.carshop.admin.dto.CategoryDto;
import com.carshop.admin.dto.CreateCategoryResource;
import com.carshop.admin.exception.BusinessException;
import com.carshop.admin.exception.CategoryNotFoundException;
import com.carshop.admin.repository.CategoryRepository;
import com.carshop.common.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDto> listAll() {
        return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
    }


    public CategoryDto createCategory(CreateCategoryResource categoryResource) {
        Optional<Category> foundCategory = categoryRepository.findCategoryByName(categoryResource.getName());
        if(foundCategory.isPresent()){
            throw new BusinessException("There is already a category with name [" + categoryResource.getName() + "]");
        }
        Category category = new Category(categoryResource.getName());
        return new CategoryDto(categoryRepository.save(category));
    }


    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Could not find category with id [" + id + "]"));
        return new CategoryDto(category);
    }

    public void deleteCategory(Long id) throws CategoryNotFoundException {
        Category foundndCategory = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Could not find category with id [" + id + "]"));
        categoryRepository.delete(foundndCategory);
    }

}
