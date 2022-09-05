package com.example.converter.converter;

import com.example.converter.utils.AES256Util;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@RequiredArgsConstructor
@Converter
public class AES256Converter implements AttributeConverter<String, String> {

    private final AES256Util aes256Util;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return aes256Util.encrypt(attribute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return aes256Util.decrypt(dbData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
