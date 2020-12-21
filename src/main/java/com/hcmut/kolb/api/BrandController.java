package com.hcmut.kolb.api;

import com.hcmut.kolb.entity.Brand;
import com.hcmut.kolb.entity.ProductCategory;
import com.hcmut.kolb.entity.ProductInfo;
import com.hcmut.kolb.repository.ProductInfoRepository;
import com.hcmut.kolb.service.CategoryService;
import com.hcmut.kolb.service.ProductService;
import com.hcmut.kolb.service.impl.BrandService;
import com.hcmut.kolb.vo.response.BrandPage;
import com.hcmut.kolb.vo.response.CategoryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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



    @GetMapping("/brand")
    public Page<Brand> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return brandService.findAll(request);
    }

//    @GetMapping("/brand/{brandId}")
//    public Brand showOne(@PathVariable("brandId") String brandId) {
//
//        Brand brand = brandService.findOne(brandId);
//
////        // Product is not available
////        if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())) {
////            productInfo = null;
////        }
//        return brand;
//    }

    @GetMapping("/brand/{type}")
    public BrandPage showOne(@PathVariable("type") String brandId,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "3") Integer size) {

//        ProductCategory cat = categoryService.findByCategoryType(categoryType);
         PageRequest request = PageRequest.of(page - 1, size);
//        Page<ProductInfo> productInCategory = productService.findAllInCategory(categoryType, request);
//        var tmp = new CategoryPage("", productInCategory);
//        tmp.setCategory(cat.getCategoryName());
//        return tmp;
         Page<ProductInfo> productInBrand = productInfoRepository.findAllByBrandIdOrderByProductIdAsc(
                brandId, request);
         return new BrandPage(brandId, productInBrand);

    }

    @GetMapping("/seller/brand/{type}")
    public Page<ProductInfo> findAllViaBrand(@PathVariable("type") String brandId,
                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return productInfoRepository.findAllByBrandIdOrderByProductIdAsc(brandId, request);
    }


    @PostMapping("/seller/brand/new")
    public ResponseEntity create(@Valid @RequestBody Brand brand,
                                 BindingResult bindingResult) {
        Brand brandIdExists = brandService.findOne(brand.getBrandId());
        if (brandIdExists != null) {
            bindingResult
                    .rejectValue("brandId", "error.brand",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(brandService.update(brand));
    }

    @PutMapping("/seller/brand/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") String brandId,
                               @Valid @RequestBody Brand brand,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!brandId.equals(brand.getBrandId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(brandService.update(brand));
    }

    @DeleteMapping("/seller/brand/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") String brandId) {
        brandService.delete(brandId);
        return ResponseEntity.ok().build();
    }

}