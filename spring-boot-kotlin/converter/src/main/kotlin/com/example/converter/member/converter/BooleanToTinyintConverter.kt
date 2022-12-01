package com.example.converter.member.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class BooleanToTinyintConverter : AttributeConverter<Boolean, Int?> {
    override fun convertToDatabaseColumn(attribute: Boolean?): Int {
        return if (attribute != null && attribute) 1 else 0
    }

    override fun convertToEntityAttribute(dbData: Int?): Boolean {
        return 1 == dbData
    }
}
