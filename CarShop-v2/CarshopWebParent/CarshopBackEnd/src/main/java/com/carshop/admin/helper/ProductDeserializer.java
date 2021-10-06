package com.carshop.admin.helper;

import com.carshop.admin.dto.CreateModelResource;
import com.carshop.admin.dto.CreateProductResource;
import com.carshop.admin.dto.ModelDto;
import com.carshop.common.entity.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public class ProductDeserializer extends StdDeserializer<CreateProductResource> {

    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CreateProductResource deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String conditie = node.get("conditie").asText();
        int model = (int) node.get("model").numberValue();
        int bouwjaar = (int) ((IntNode) node.get("bouwjaar")).numberValue();
        String koetswerkkleur = node.get("koetswerkkleur").asText();
        int aantalDeuren = (int) node.get("aantalDeuren").numberValue();
        int aantalKm = (int) node.get("aantalKm").numberValue();
        String transmissie = node.get("transmissie").asText();
        int prijs = (int) node.get("prijs").numberValue();

        return new CreateProductResource(Conditie.valueOf(conditie.toUpperCase()), new Model((long) model),
                bouwjaar, koetswerkkleur, aantalDeuren, Transmissie.valueOf(transmissie.toUpperCase()),
                aantalKm, prijs, null);
    }

}
