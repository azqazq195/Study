package com.example.jpaquerydsl.pairing.domain

import com.example.jpaquerydsl._common.domain.BaseEntity
import jakarta.persistence.Entity

@Entity
class Pairing(
    name: String,
) : BaseEntity() {
    var name: String = name
        protected set

    fun update(updateValue: Pairing) {
        this.name = updateValue.name
    }
}