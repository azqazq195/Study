package com.example.converter.member.converter

import com.example.converter.utils.AES256Util
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class AES256Converter(
    private val aes256Util: AES256Util
) : AttributeConverter<String, String>{
    override fun convertToDatabaseColumn(attribute: String?): String {
        if (attribute == null) {
            return ""
        }
        return aes256Util.encrypt(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): String {
        if (dbData == null) {
            return ""
        }
        return aes256Util.decrypt(dbData);
    }
}