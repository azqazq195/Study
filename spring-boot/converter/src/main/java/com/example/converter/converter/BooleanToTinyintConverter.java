package com.example.converter.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToTinyintConverter implements AttributeConverter<Boolean, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? 1 : 0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return 1 == dbData;
    }
}
