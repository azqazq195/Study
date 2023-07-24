package com.example.jpaquerydsl.region.domain

import com.example.jpaquerydsl._common.domain.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Region(
    nameKorean: String,
    nameEnglish: String,
    parent: Region? = null,
) : BaseEntity() {
    var nameKorean: String = nameKorean
        protected set

    var nameEnglish: String = nameEnglish
        protected set

    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parent: Region? = parent
        protected set

    fun update(updateValue: Region) {
        this.nameKorean = updateValue.nameKorean
        this.nameEnglish = updateValue.nameEnglish
        this.parent = updateValue.parent
    }
}