package com.carshop.admin.service;

import com.carshop.admin.dto.CreateModelResource;
import com.carshop.admin.dto.ModelDto;
import com.carshop.admin.exception.BusinessException;
import com.carshop.admin.exception.ModelNotFoundException;
import com.carshop.admin.repository.BrandRepository;
import com.carshop.admin.repository.BrandstofRepository;
import com.carshop.admin.repository.CategoryRepository;
import com.carshop.admin.repository.ModelRepository;
import com.carshop.common.entity.Brand;
import com.carshop.common.entity.Brandstof;
import com.carshop.common.entity.Category;
import com.carshop.common.entity.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelService.class);

    public static final int MODELS_PER_PAGE = 10;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandstofRepository brandstofRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ModelDto> listAll() {
        return modelRepository.findAll().stream().map(ModelDto::new).collect(Collectors.toList());
    }

    public ModelDto createModel(CreateModelResource createModelResource) {
        Optional<Model> foundModel = modelRepository.findModelByBrandAndName(createModelResource.getBrand().getName(), createModelResource.getName());
        if(foundModel.isPresent()){
            throw new BusinessException("There is already a model [" + createModelResource.getName() + "] from brand [" + createModelResource.getBrand().getName() + "]");
        }
        Model model = new Model(createModelResource.getName(), createModelResource.getGeneratie(), createModelResource.getCategory(),
                createModelResource.getBrand(), createModelResource.getBrandstof(),
                createModelResource.getBrandstofScore(), createModelResource.getAantalPk(),
                createModelResource.getAandrijving(), createModelResource.getCo2Emissie(),
                createModelResource.getEmissieKlasse());
        return new ModelDto(modelRepository.save(model));
    }

    public ModelDto getModel(Long id){
        Model foundModel = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Could not find model with id [" + id + "]"));
        return new ModelDto(foundModel);
    }

    public void deleteModel(Long id){
        Model foundModel = modelRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Could not find and delete model with id [" + id + "]"));
        modelRepository.delete(foundModel);
    }

    @Transactional
    public void updateModel(Long id, CreateModelResource modelResource){
        modelRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("Could not find and delete model with id [" + id + "]"));
        modelRepository.updateModel(id, modelResource.getName(), modelResource.getGeneratie(), modelResource.getCategory(), modelResource.getBrand(),
                modelResource.getBrandstof(), modelResource.getBrandstofScore(), modelResource.getAantalPk(),
                modelResource.getAandrijving(), modelResource.getCo2Emissie(), modelResource.getEmissieKlasse());
    }


}
