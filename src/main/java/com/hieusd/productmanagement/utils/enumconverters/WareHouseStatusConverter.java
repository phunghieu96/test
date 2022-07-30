package com.hieusd.productmanagement.utils.enumconverters;

import com.hieusd.productmanagement.constants.CategoryStatus;
import com.hieusd.productmanagement.constants.WareHouseStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class WareHouseStatusConverter implements AttributeConverter<WareHouseStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(WareHouseStatus wareHouseStatus) {
        if(wareHouseStatus == null) return null;

        return wareHouseStatus.getStatus();
    }

    @Override
    public WareHouseStatus convertToEntityAttribute(Integer status) {
        if(status == null) return null;

        return Stream.of(WareHouseStatus.values())
                .filter(value -> value.getStatus() == status)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
