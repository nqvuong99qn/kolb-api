package com.hcmut.kolb.api;

import com.hcmut.kolb.Response.SucessResponse;
import com.hcmut.kolb.entity.Brand;
import com.hcmut.kolb.entity.ProductCategory;
import com.hcmut.kolb.entity.ProductInfo;
import com.hcmut.kolb.repository.BrandRepository;
import com.hcmut.kolb.repository.ProductInfoRepository;
import com.hcmut.kolb.service.CategoryService;
import com.hcmut.kolb.service.ProductService;
import com.hcmut.kolb.service.impl.BrandService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class BrandController {

    @Autowired
    BrandService brandService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductInfoRepository productInfoRepository;

    @Autowired
    BrandRepository brandRepository;



    // show all brand
    @GetMapping("/brands")
    public SucessResponse findAll(){

        return new SucessResponse().put("meta", null).
                put("data", new SucessResponse().put("brands", brandRepository.findAll()));
    }

    // show one
    @GetMapping("/brand/{type}")
    public SucessResponse getOne(@PathVariable("type") String brandId) {
        return new SucessResponse().put("meta", null).
                put("data", new SucessResponse().put("brand", brandRepository.findById(brandId)));

    }
}