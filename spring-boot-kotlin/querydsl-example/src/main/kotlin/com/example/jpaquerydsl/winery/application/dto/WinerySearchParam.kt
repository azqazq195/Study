package com.example.jpaquerydsl.winery.application.dto

data class WinerySearchParam(
    val wineryNameKorean: String?,
    val wineryNameEnglish: String?,
    val regionNameKorean: String?,
    val regionNameEnglish: String?,
)