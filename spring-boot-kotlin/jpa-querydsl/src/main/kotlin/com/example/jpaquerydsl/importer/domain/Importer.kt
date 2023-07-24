package com.example.jpaquerydsl.importer.domain

import com.example.jpaquerydsl._common.domain.BaseEntity
import jakarta.persistence.Entity

@Entity
class Importer(
    name: String,
) : BaseEntity() {
    var name: String = name
        protected set

    fun update(updateValue: Importer) {
        this.name = updateValue.name
    }
}