package com.carshop.admin.controller;

import com.carshop.admin.dto.BrandstofDto;
import com.carshop.admin.service.BrandstofTransmissieService;
import com.carshop.common.entity.Aandrijving;
import com.carshop.common.entity.Conditie;
import com.carshop.common.entity.Transmissie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/services/brandstoftransmissie")
@CrossOrigin(origins = "http://localhost:4200")
public class BrandstofTransmissieController {

    @Autowired
    private BrandstofTransmissieService brandstofTransmissieService;

    @GetMapping("/brandstof")
    public List<BrandstofDto> listAllBrandstof() {
        return brandstofTransmissieService.listAllBrandstof();
    }

    @GetMapping(value = "/transmissie", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transmissie> listAllTransmissies() {
        return brandstofTransmissieService.listAllTransmissies();
    }

    @GetMapping(value = "/aandrijving", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Aandrijving> listAllAandrijving() {
        return brandstofTransmissieService.listAllAandrijving();
    }

    @GetMapping(value = "/conditie", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Conditie> listAllCondities() {
        return brandstofTransmissieService.listAllCondities();
    }
}
