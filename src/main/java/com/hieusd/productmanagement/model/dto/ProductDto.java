package com.hieusd.productmanagement.model.dto;

import com.hieusd.productmanagement.constants.ProductStatus;
import com.hieusd.productmanagement.model.entity.Category;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ProductDto {
    private Long id;

    @NotBlank(message = "Ten sp khong duoc de trong")
    @Length(min = 1, max = 100, message = "Ten san pham khong duoc qua 100 ki tu")
    private String name;

    @Positive(message = "Gia sp khong duoc <= 0")
    private double price;

    private String sku;

    private ProductStatus status;

    private String description;

    @NotNull(message = "Thieu thong tin cua category")
    private Category category;
}
