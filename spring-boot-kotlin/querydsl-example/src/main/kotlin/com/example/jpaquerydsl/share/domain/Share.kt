package com.example.jpaquerydsl.share.domain

import com.example.jpaquerydsl._common.domain.BaseEntity
import com.example.jpaquerydsl.grape.domain.Grape
import com.example.jpaquerydsl.region.domain.Region
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Share(
    share: Double,
    grape: Grape,
    region: Region,
) : BaseEntity() {
    var share: Double = share
        protected set

    @ManyToOne
    @JoinColumn(name = "grape_id")
    var grape: Grape = grape
        protected set

    @ManyToOne
    @JoinColumn(name = "region_id")
    var region: Region = region
        protected set

    fun update(updateValue: Share) {
        this.share = updateValue.share
        this.region = updateValue.region
        this.grape = updateValue.grape
    }
}