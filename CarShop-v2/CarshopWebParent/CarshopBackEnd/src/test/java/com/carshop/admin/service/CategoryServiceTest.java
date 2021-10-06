package com.carshop.admin.service;

import com.carshop.admin.dto.CategoryDto;
import com.carshop.admin.dto.CreateCategoryResource;
import com.carshop.admin.exception.CategoryNotFoundException;
import com.carshop.admin.repository.CategoryRepository;
import com.carshop.common.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    public void init(){
        category = new Category("testCategory");
    }

    @Test
    public void createCategory(){
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CreateCategoryResource categoryResource = new CreateCategoryResource(category.getName());

        CategoryDto categoryDto = categoryService.createCategory(categoryResource);
        System.out.println(categoryDto.getName());
        assertThat(categoryDto.getName()).isEqualTo(categoryResource.getName());
    }

    @Test
    public void getCategoryReturnsExceptionWhenIdNotFound(){
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.empty());
        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategory(category.getId()));
    }

    @Test
    public void getCategoryReturnsCategoryWithIdGiven(){
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        CategoryDto categoryDto = categoryService.getCategory(category.getId());

        assertEquals(category.getId(), categoryDto.getId());
    }

    @Test
    public void deleteCategoryOk(){
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        categoryService.deleteCategory(category.getId());
        Mockito.verify(categoryRepository).delete(category);
    }

    @Test
    public void listAllCategoriesReturnsList(){
        when(categoryRepository.findAll()).thenReturn(List.of(category));
        categoryService.listAll();
        Mockito.verify(categoryRepository).findAll();
    }
}
