package com.carshop.admin.service;

import com.carshop.admin.dto.BrandDto;
import com.carshop.admin.dto.CreateBrandResource;
import com.carshop.admin.exception.BrandNotFoundException;
import com.carshop.admin.repository.BrandRepository;
import com.carshop.common.entity.*;
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
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    private Brand brand;

    @BeforeEach
    public void init(){
        brand = new Brand("testBrand", "testCountry");
    }

    @Test
    public void createBrand(){
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        CreateBrandResource brandResource = new CreateBrandResource(brand.getName(), brand.getCountry());
        BrandDto brandDto = brandService.createBrand(brandResource);

        assertEquals(brandDto.getName(), brandResource.getName());
    }

    @Test
    public void getBrandReturnsExceptionWhenIdNotFound(){
        when(brandRepository.findById(brand.getId())).thenReturn(Optional.empty());
        assertThrows(BrandNotFoundException.class, () -> brandService.getBrand(brand.getId()));
    }

    @Test
    public void getBrandReturnsBrandWhenIdFound(){
        when(brandRepository.findById(brand.getId())).thenReturn(Optional.of(brand));
        BrandDto brandDto = brandService.getBrand(brand.getId());
        assertEquals(brand.getName(), brandDto.getName());
    }

    @Test
    public void deleteBrandReturnsOk(){
        when(brandRepository.findById(brand.getId())).thenReturn(Optional.of(brand));
        brandService.deleteBrand(brand.getId());
        Mockito.verify(brandRepository).delete(brand);
    }

    @Test
    public void listAllBrandsReturnsList(){
        when(brandRepository.findAll()).thenReturn(List.of(brand));
        brandService.listAll();
        Mockito.verify(brandRepository).findAll();
    }

    //TODO test updateModel
}
