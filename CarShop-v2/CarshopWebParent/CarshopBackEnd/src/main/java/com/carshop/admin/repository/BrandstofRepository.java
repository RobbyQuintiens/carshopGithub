package com.carshop.admin.repository;

import com.carshop.common.entity.Brand;
import com.carshop.common.entity.Brandstof;
import com.carshop.common.entity.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandstofRepository extends JpaRepository<Brandstof, Long> {


}
