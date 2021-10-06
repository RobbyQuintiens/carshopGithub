package com.carshop.admin.service;

import com.carshop.admin.dto.CategoryDto;
import com.carshop.admin.dto.CreateCategoryResource;
import com.carshop.admin.dto.CreateModelResource;
import com.carshop.admin.dto.ModelDto;
import com.carshop.admin.exception.ModelNotFoundException;
import com.carshop.admin.repository.CategoryRepository;
import com.carshop.admin.repository.ModelRepository;
import com.carshop.common.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModelServiceTest {

    @Mock
    private ModelRepository modelRepository;

    @InjectMocks
    private ModelService modelService;

    private Model model;

    @BeforeEach
    public void init(){
        model = new Model("name", "(2017-2019)", new Category("category"), new Brand("brand", "country"),
                new Brandstof("brandstof"), "B", 100, Aandrijving.ACHTERWIEL,
                100, "D");
    }

    @Test
    public void createModel(){
        when(modelRepository.save(any(Model.class))).thenReturn(model);
        CreateModelResource modelResource = new CreateModelResource(model.getName(), model.getGeneratie(), model.getCategory(),
                model.getBrand(), model.getBrandstof(), model.getBrandstofScore(), model.getAantalPk(),
                model.getAandrijving(), model.getCo2Emissie(), model.getEmissieKlasse());

        ModelDto modelDto = modelService.createModel(modelResource);

        assertEquals(modelDto.getName(), modelResource.getName());
    }

    @Test
    public void getModelReturnsExceptionWhenIdNotFound(){
        when(modelRepository.findById(model.getId())).thenReturn(Optional.empty());
        assertThrows(ModelNotFoundException.class, () -> modelService.getModel(model.getId()));
    }

    @Test
    public void getModelReturnsModelWhenIdFound(){
        when(modelRepository.findById(model.getId())).thenReturn(Optional.of(model));
        ModelDto modelDto = modelService.getModel(model.getId());
        assertEquals(model.getId(), modelDto.getId());
    }

    @Test
    public void deleteModelReturnsOk(){
        when(modelRepository.findById(model.getId())).thenReturn(Optional.of(model));
        modelService.deleteModel(model.getId());
        Mockito.verify(modelRepository).delete(model);
    }

    @Test
    public void listAllModelsReturnsList(){
        when(modelRepository.findAll()).thenReturn(List.of(model));
        modelService.listAll();
        Mockito.verify(modelRepository).findAll();
    }

    //TODO test updateModel

}
