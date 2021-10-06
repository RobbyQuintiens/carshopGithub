package com.carshop.admin.service;

import com.carshop.admin.dto.BrandDto;
import com.carshop.admin.dto.CreateBrandResource;
import com.carshop.admin.dto.ModelDto;
import com.carshop.admin.exception.BrandNotFoundException;
import com.carshop.admin.exception.BusinessException;
import com.carshop.admin.repository.BrandRepository;
import com.carshop.common.entity.Brand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandService.class);

    public static final int BRANDS_PER_PAGE = 10;

    @Autowired
    private BrandRepository brandRepository;

    public List<BrandDto> listAll() {
        return brandRepository.findAll().stream().map(BrandDto::new).collect(Collectors.toList());
    }

    public BrandDto createBrand(CreateBrandResource brandResource) {
        Optional<Brand> foundBrand = brandRepository.findBrandByName(brandResource.getName());
        if(foundBrand.isPresent()){
            throw new BusinessException("There is already a brand with name [" + brandResource.getName() + "]");
        }
        Brand brand = new Brand(brandResource.getName(), brandResource.getCountry());
        return new BrandDto(brandRepository.save(brand));
    }

    public BrandDto getBrand(Long id) throws BrandNotFoundException {
        Brand foundBrand = brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException("Could not find brand with id [" + id + "]"));
        return new BrandDto(foundBrand);
    }

    public void deleteBrand(Long id) throws BrandNotFoundException {
        Brand foundBrand = brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException("Could not delete brand with id [" + id + "]"));
        brandRepository.delete(foundBrand);
    }

    @Transactional
    public void updateBrand(Long id, CreateBrandResource brandResource) throws BrandNotFoundException {
        brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException("Could not find brand with id [" + id + "]"));
        brandRepository.updateBrand(id, brandResource.getName(), brandResource.getCountry());
    }

    //TODO list all models from SET<Model>

    public List<ModelDto> findModels(Long id){
        Brand foundBrand = brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException("Could not find brand with id [" + id + "]"));
        return foundBrand.getModels().stream().map(ModelDto::new).collect(Collectors.toList());
    }
}
