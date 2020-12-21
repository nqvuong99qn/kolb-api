package com.hcmut.kolb.vo.response;

import lombok.Data;


@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String account;
    private String name;
    private String role;
    private String brandId;

    public JwtResponse(String token, String account, String name, String role, String brandId) {
        this.account = account;
        this.name = name;
        this.token = token;
        this.role = role;
        this.brandId = brandId;
    }
}
