package com.example.jpaquerydsl.winery.domain.repository

import com.example.jpaquerydsl.winery.domain.Winery
import com.example.jpaquerydsl.winery.exception.NotFoundWineryException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WineryRepository : JpaRepository<Winery, Long>

fun WineryRepository.getEntityById(id: Long): Winery =
    findById(id).orElseThrow(::NotFoundWineryException)