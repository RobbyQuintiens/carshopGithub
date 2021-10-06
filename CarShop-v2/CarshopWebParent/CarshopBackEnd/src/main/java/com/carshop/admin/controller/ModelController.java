package com.carshop.admin.controller;

import com.carshop.admin.dto.CreateModelResource;
import com.carshop.admin.dto.ModelDto;
import com.carshop.admin.helper.ModelDeserializer;
import com.carshop.admin.service.ModelService;
import com.carshop.common.entity.Brand;
import com.carshop.common.entity.Brandstof;
import com.carshop.common.entity.Category;
import com.carshop.common.entity.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/services/models")
@CrossOrigin(origins = "http://localhost:4200")
public class ModelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private ModelService modelService;

    @GetMapping
    public List<ModelDto> listAll() {
        return modelService.listAll();
    }

    @GetMapping("/{id}")
    public ModelDto getModel(@PathVariable(name = "id") Long id) {
        return modelService.getModel(id);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Void> saveModel(@RequestBody String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(CreateModelResource.class, new ModelDeserializer());
        mapper.registerModule(module);
        CreateModelResource createModelResource = mapper.readValue(jsonString, CreateModelResource.class);
        modelService.createModel(createModelResource);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable(name = "id") Long id) {
        modelService.deleteModel(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> updateModel(@PathVariable(name = "id") Long id,
                                            @RequestBody @Valid CreateModelResource modelResource){
        modelService.updateModel(id, modelResource);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
