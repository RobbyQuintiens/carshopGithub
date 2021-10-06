package com.carshop.admin.service;

import com.carshop.admin.dto.BrandstofDto;
import com.carshop.admin.repository.BrandstofRepository;
import com.carshop.common.entity.Aandrijving;
import com.carshop.common.entity.Conditie;
import com.carshop.common.entity.Transmissie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandstofTransmissieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandstofTransmissieService.class);

    @Autowired
    private BrandstofRepository brandstofRepository;

    public List<BrandstofDto> listAllBrandstof() {
        return brandstofRepository.findAll().stream().map(BrandstofDto::new).collect(Collectors.toList());
    }

    public List<Transmissie> listAllTransmissies() {
        return Arrays.asList(Transmissie.values().clone());
    }

    public List<Aandrijving> listAllAandrijving() {
        return Arrays.asList(Aandrijving.values().clone());
    }

    public List<Conditie> listAllCondities() {
        return Arrays.asList(Conditie.values().clone());
    }

}
