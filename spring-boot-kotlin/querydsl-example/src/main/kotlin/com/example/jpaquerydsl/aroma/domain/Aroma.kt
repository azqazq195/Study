package com.example.jpaquerydsl.aroma.domain

import com.example.jpaquerydsl._common.domain.BaseEntity
import jakarta.persistence.Entity

@Entity
class Aroma(
    name: String,
) : BaseEntity() {
    var name: String = name
        protected set

    fun update(updateValue: Aroma) {
        this.name = updateValue.name
    }
}