package com.carshop.admin.repository;

import com.carshop.common.entity.Brand;
import com.carshop.common.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findBrandByName(String name);

    Optional<Brand> findBrandByCountry(String country);

    Optional<Brand> findBrandByModels(Model model);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Brand b SET b.name = :name, b.country = :country WHERE b.id = :id")
    void updateBrand(@Param("id") Long id, @Param("name") String name, @Param("country") String country);


}
