package com.hcmut.kolb.service.impl;

import com.hcmut.kolb.entity.Brand;

import com.hcmut.kolb.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;


    public Brand findOne(String brandId) {
        return brandRepository.findByBrandId(brandId);
    }

    public Page<Brand> findAll(Pageable pageable) {
        return brandRepository.findAllByOrderByBrandId(pageable);
    }


    public Brand update(Brand brand) {


        return brandRepository.save(brand);
    }


//    public void delete(String brandId) {
//        Brand brand = findOne(brandId);
//        if (brand== null) throw new MyException(ResultEnum.PRODUCT_NOT_EXIST);
//
//        brandRepository.delete(brand);
//    }


}