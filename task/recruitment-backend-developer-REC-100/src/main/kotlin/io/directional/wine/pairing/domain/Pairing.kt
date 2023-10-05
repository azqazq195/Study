package io.directional.wine.pairing.domain

import io.directional.wine._common.domain.BaseEntity
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