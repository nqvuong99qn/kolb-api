package com.hcmut.kolb.vo.response;

import com.hcmut.kolb.entity.ProductInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;


@Data
@Setter
@Getter
public class BrandPage {
    private String brandId;
    private Page<ProductInfo> page;

    public BrandPage(String brandId, Page<ProductInfo> page) {
        this.brandId = brandId;
        this.page = page;
    }

}
