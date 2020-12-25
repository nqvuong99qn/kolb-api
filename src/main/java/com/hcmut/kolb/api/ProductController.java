package com.hcmut.kolb.api;

import com.hcmut.kolb.Response.SucessResponse;
import com.hcmut.kolb.entity.ProductInfo;
import com.hcmut.kolb.repository.ProductInfoRepository;
import com.hcmut.kolb.service.CategoryService;
import com.hcmut.kolb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @Autowired
    ProductInfoRepository productInfoRepository;

    /**
     * Show All Categories
     */

//    @GetMapping("/all-products")
//    public Page<ProductInfo> findAllPaging(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                     @RequestParam(value = "size", defaultValue = "3") Integer size) {
//        PageRequest request = PageRequest.of(page - 1, size);
//        return productService.findAll(request);
//    }


    @GetMapping("/products")
    public SucessResponse findAll(){

        return new SucessResponse().put("meta", null).
                put("data", new SucessResponse().put("products", productInfoRepository.findAll()));
    }

    @GetMapping("/product/{productId}")
    public SucessResponse showOne(@PathVariable("productId") String productId) {

        ProductInfo productInfo = productService.findOne(productId);

        return new SucessResponse().put("meta", null).put("data", new SucessResponse().put(
                "productInfor", productInfo) );
    }

    @PostMapping("/seller/product/new")
    public ResponseEntity create(@Valid @RequestBody ProductInfo product,
                                 BindingResult bindingResult) {
        ProductInfo productIdExists = productService.findOne(product.getProductId());
        if (productIdExists != null) {
            bindingResult
                    .rejectValue("productId", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/seller/product/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") String productId,
                               @Valid @RequestBody ProductInfo product,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getProductId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/seller/product/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") String productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

}
