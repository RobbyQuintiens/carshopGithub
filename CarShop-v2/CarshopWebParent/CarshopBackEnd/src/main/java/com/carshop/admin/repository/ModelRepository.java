package com.carshop.admin.repository;

import com.carshop.common.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<Model> findModelByName(String name);

    List<Model> findModelsByBrandstof(Brandstof brandstof);

    List<Model> findModelsByCategory(Category category);

    List<Model> findModelsByBrand(Brand brand);

    Optional<Model> findModelByBrandAndName(String brand, String model);

    Optional<Model> findModelByBrandAndNameAndCode(String brand, String model, String code);

    Optional<Model> deleteById(long id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Model m SET m.name = :name, m.generatie = :generatie, m.category = :category, " +
            "m.brand = :brand, m.brandstof = :brandstof, m.brandstofScore = :brandstofScore," +
            "m.aantalPk = :aantalPk, m.aandrijving = :aandrijving, m.co2Emissie = :co2Emissie," +
            "m.emissieKlasse = :emissieKlasse WHERE m.id = :id")
    void updateModel(@Param("id") Long id, @Param("name") String name, @Param("generatie") String generatie, @Param("category") Category category,
                     @Param("brand") Brand brand, @Param("brandstof") Brandstof brandstof,
                     @Param("brandstofScore") String brandstofScore, @Param("aantalPk") int aantalPk,
                     @Param("aandrijving") Aandrijving aandrijving, @Param("co2Emissie") int co2Emissie,
                     @Param("emissieKlasse") String emissieKlasse);

}
