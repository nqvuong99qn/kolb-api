package com.hcmut.kolb.api;


import com.hcmut.kolb.Response.SucessResponse;
import com.hcmut.kolb.entity.ProductCategory;
import com.hcmut.kolb.entity.ProductInfo;
import com.hcmut.kolb.repository.BrandRepository;
import com.hcmut.kolb.repository.ProductCategoryRepository;
import com.hcmut.kolb.service.CategoryService;
import com.hcmut.kolb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    /**
     * Show products in category
     *
     * @param categoryType
     * @param page
     * @param size
     * @return
     */
//    @GetMapping("/category/{type}")
//    public CategoryPage showOne(@PathVariable("type") Integer categoryType,
//                                @RequestParam(value = "page", defaultValue = "1") Integer page,
//                                @RequestParam(value = "size", defaultValue = "3") Integer size) {
//
//        ProductCategory cat = categoryService.findByCategoryType(categoryType);
//        PageRequest request = PageRequest.of(page - 1, size);
//        Page<ProductInfo> productInCategory = productService.findAllInCategory(categoryType, request);
//        var tmp = new CategoryPage("", productInCategory);
//        tmp.setCategory(cat.getCategoryName());
//        return tmp;
//    }

//    @GetMapping("/category/{type}")
//    public SucessResponse findAllProductsInCategory(@PathVariable("type") Integer categoryType){
//        productService.findAllInCategory(categoryType);
//        return new SucessResponse().put("meta", null).
//                put("data", new SucessResponse().put("brands", brandRepository.findAll()));
//    }

    @GetMapping("/categories")
    public SucessResponse findAll(){

        return new SucessResponse().put("meta", null).
                put("data", new SucessResponse().put("categories", productCategoryRepository.findAll()));
    }

    // show one
    @GetMapping("/category/{type}")
    public SucessResponse getOne(@PathVariable("type") String categoryId) {
        return new SucessResponse().put("meta", null).
                put("data", new SucessResponse().put("category", productCategoryRepository.findById(categoryId)));

    }
}
