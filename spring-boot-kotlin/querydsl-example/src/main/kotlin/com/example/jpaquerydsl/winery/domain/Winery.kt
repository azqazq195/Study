package com.example.jpaquerydsl.winery.domain

import com.example.jpaquerydsl._common.domain.BaseEntity
import com.example.jpaquerydsl.region.domain.Region
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Winery(
    nameKorean: String,
    nameEnglish: String,
    region: Region
) : BaseEntity() {
    var nameKorean: String = nameKorean
        protected set

    var nameEnglish: String = nameEnglish
        protected set

    @ManyToOne
    @JoinColumn(name = "region_id")
    var region: Region = region
        protected set

    fun update(updateValue: Winery) {
        this.nameKorean = updateValue.nameKorean
        this.nameEnglish = updateValue.nameEnglish
        this.region = updateValue.region
    }
}