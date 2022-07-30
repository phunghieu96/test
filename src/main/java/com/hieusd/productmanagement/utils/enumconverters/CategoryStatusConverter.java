package com.hieusd.productmanagement.utils.enumconverters;

import com.hieusd.productmanagement.constants.CategoryStatus;
import com.hieusd.productmanagement.constants.ProductStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CategoryStatusConverter implements AttributeConverter<CategoryStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CategoryStatus categoryStatus) {
        if (categoryStatus == null) return null;
        return categoryStatus.getStatus();
    }


    @Override
    public CategoryStatus convertToEntityAttribute(Integer status) {
        if (status == null) return null;

        return Stream.of(CategoryStatus.values())
                .filter(value -> value.getStatus() == status)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
