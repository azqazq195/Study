package com.example.jpaquerydsl.wine.domain

import com.example.jpaquerydsl._common.domain.BaseEntity
import com.example.jpaquerydsl.aroma.domain.Aroma
import com.example.jpaquerydsl.grape.domain.Grape
import com.example.jpaquerydsl.importer.domain.Importer
import com.example.jpaquerydsl.pairing.domain.Pairing
import com.example.jpaquerydsl.region.domain.Region
import com.example.jpaquerydsl.winery.domain.Winery
import jakarta.persistence.*

@Entity
class Wine(
    type: Type,
    nameKorean: String,
    nameEnglish: String,
    alcohol: Double,
    acidity: Int,
    body: Int,
    sweetness: Int,
    tannin: Int,
    servingTemperature: Double?,
    score: Double,
    price: Int,
    style: String?,
    grade: String?,
    winery: Winery,
    region: Region,
    grapes: MutableList<Grape> = mutableListOf(),
    importer: Importer,
    aromas: MutableList<Aroma> = mutableListOf(),
    pairings: MutableList<Pairing> = mutableListOf(),
) : BaseEntity() {
    @Enumerated(EnumType.STRING)
    var type: Type = type
        protected set

    var nameKorean: String = nameKorean
        protected set

    var nameEnglish: String = nameEnglish
        protected set

    var alcohol: Double = alcohol
        protected set

    var acidity: Int = acidity
        protected set

    var body: Int = body
        protected set

    var sweetness: Int = sweetness
        protected set

    var tannin: Int = tannin
        protected set

    var servingTemperature: Double? = servingTemperature
        protected set

    var score: Double = score
        protected set

    var price: Int = price
        protected set

    var style: String? = style
        protected set

    var grade: String? = grade
        protected set

    @ManyToOne
    @JoinColumn(name = "winery_id")
    var winery: Winery = winery
        protected set

    @ManyToOne
    @JoinColumn(name = "region_id")
    var region: Region = region
        protected set

    @ManyToMany
    @JoinTable(
        name = "wine_grapes",
        joinColumns = [JoinColumn(name = "grape_id")],
        inverseJoinColumns = [JoinColumn(name = "wine_id")]
    )
    var grapes: MutableList<Grape> = grapes
        protected set

    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinColumn(name = "importer_id")
    var importer: Importer = importer
        protected set

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var aromas: MutableList<Aroma> = aromas
        protected set

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var pairings: MutableList<Pairing> = pairings
        protected set

    fun update(updateValue: Wine) {
        this.type = updateValue.type
        this.nameKorean = updateValue.nameKorean
        this.nameEnglish = updateValue.nameEnglish
        this.alcohol = updateValue.alcohol
        this.acidity = updateValue.acidity
        this.body = updateValue.body
        this.sweetness = updateValue.sweetness
        this.tannin = updateValue.tannin
        this.servingTemperature = updateValue.servingTemperature
        this.score = updateValue.score
        this.price = updateValue.price
        this.style = updateValue.style
        this.grade = updateValue.grade
        this.winery = updateValue.winery
        this.region = updateValue.region
        this.importer = updateValue.importer
        this.grapes = updateValue.grapes
        this.aromas = updateValue.aromas
        this.pairings = updateValue.pairings
    }
}