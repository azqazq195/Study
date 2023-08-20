package com.example.jpaquerydsl.share.domain.repository

import com.example.jpaquerydsl.share.domain.Share
import com.example.jpaquerydsl.share.exception.NotFoundShareException
import org.springframework.data.jpa.repository.JpaRepository

interface ShareRepository : JpaRepository<Share, Long>

fun ShareRepository.getEntityById(id: Long): Share =
    findById(id).orElseThrow(::NotFoundShareException)