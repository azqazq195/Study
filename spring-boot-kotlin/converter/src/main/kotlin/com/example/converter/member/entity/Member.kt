package com.example.converter.member.entity

import com.example.converter.member.converter.AES256Converter
import com.example.converter.member.converter.BooleanToTinyintConverter
import jakarta.persistence.*

@Entity
data class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long?,

    val name: String,

    @Convert(converter = AES256Converter::class)
    val regNo: String,

    @Convert(converter = BooleanToTinyintConverter::class)
    val deleted: Boolean = false
)