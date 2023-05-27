package com.example.springbootkotlinexample.common.base.controller.dto.filter

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.Any
import kotlin.String

enum class FieldType {
    BOOLEAN {
        override fun parse(value: String): Any {
            return value.toBoolean()
        }
    },
    CHAR {
        override fun parse(value: String): Any {
            return value[0]
        }
    },
    DATE {
        override fun parse(value: String): Any {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
            return LocalDateTime.parse(value, formatter)
        }
    },
    DOUBLE {
        override fun parse(value: String): Any {
            return value.toDouble()
        }
    },
    INTEGER {
        override fun parse(value: String): Any {
            return value.toInt()
        }
    },
    LONG {
        override fun parse(value: String): Any {
            return value.toLong()
        }
    },
    STRING {
        override fun parse(value: String): Any {
            return value
        }
    };

    abstract fun parse(value: String): Any
}
