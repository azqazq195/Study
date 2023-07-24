package com.example.jpaquerydsl.grape.exception

import com.example.jpaquerydsl._common.exception.NotFoundEntityException
import com.example.jpaquerydsl.grape.domain.Grape

class NotFoundGrapeException : NotFoundEntityException(Grape::class.java)
