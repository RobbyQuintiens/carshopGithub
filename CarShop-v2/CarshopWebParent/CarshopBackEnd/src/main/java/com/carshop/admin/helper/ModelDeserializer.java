package com.carshop.admin.helper;

import com.carshop.admin.dto.CreateModelResource;
import com.carshop.common.entity.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;

import java.io.IOException;

public class ModelDeserializer extends StdDeserializer<CreateModelResource> {


    public ModelDeserializer() {
        this(null);
    }

    public ModelDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CreateModelResource deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        String generatie = node.get("generatie").asText();
        int category = (int) ((IntNode) node.get("category")).numberValue();
        int brand = (int) ((IntNode)node.get("brand")).numberValue();
        int brandstof = (int) ((IntNode)node.get("brandstof")).numberValue();
        String brandstofScore = node.get("brandstofScore").asText();
        int aantalPk = (int) node.get("aantalPk").numberValue();
        String aandrijving = node.get("aandrijving").asText();
        int co2Emissie = (int) node.get("co2Emissie").numberValue();
        String emissieKlasse = node.get("emissieKlasse").asText();

        return new CreateModelResource(name, generatie, new Category((long) category),
                new Brand((long) brand), new Brandstof((long) brandstof),
                brandstofScore, aantalPk,
                Aandrijving.valueOf(aandrijving.toUpperCase()), co2Emissie, emissieKlasse);
    }
}