package com.hcmut.kolb.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
public class Brand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String brandId;

    @NotNull
    private String brandName;

    @NotNull
    private String brandAddress;

    @NotNull
    private String brandPhone;

    @Column(columnDefinition="text")
    private String brandDescription = "";

    private String logo;

    @CreationTimestamp
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;

    @Nullable
    private String emailSeller;

}