package io.directional.wine.grape.domain

import io.directional.wine._common.domain.BaseEntity
import io.directional.wine.wine.domain.Wine
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany

@Entity
class Grape(
    nameKorean: String,
    nameEnglish: String,
    acidity: Int,
    body: Int,
    sweetness: Int,
    tannin: Int,
) : BaseEntity() {
    var nameKorean: String = nameKorean
        protected set

    var nameEnglish: String = nameEnglish
        protected set

    var acidity: Int = acidity
        protected set

    var body: Int = body
        protected set

    var sweetness: Int = sweetness
        protected set

    var tannin: Int = tannin
        protected set

    @ManyToMany
    @JoinTable(
        name = "wine_grapes",
        joinColumns = [JoinColumn(name = "grape_id")],
        inverseJoinColumns = [JoinColumn(name = "wine_id")]
    )
    var wines: MutableList<Wine> = mutableListOf()
        protected set

    fun update(updateValue: Grape) {
        this.nameKorean = updateValue.nameKorean
        this.nameEnglish = updateValue.nameEnglish
        this.acidity = updateValue.acidity
        this.body = updateValue.body
        this.sweetness = updateValue.sweetness
        this.tannin = updateValue.tannin
    }
}
