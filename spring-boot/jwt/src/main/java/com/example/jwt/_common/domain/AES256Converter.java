package com.example.jwt._common.domain;

import com.example.jwt._common.util.AES256Utils;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter
public class AES256Converter implements AttributeConverter<String, String> {

    private final AES256Utils aes256Utils;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return aes256Utils.encrypt(attribute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return aes256Utils.decrypt(dbData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
