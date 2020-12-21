package com.hcmut.kolb.repository;

import com.hcmut.kolb.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BrandRepository extends JpaRepository<Brand, String> {

    Brand findByBrandId(String id);
    // onsale product
    Page<Brand> findAllByOrderByBrandId(Pageable pageable);

}