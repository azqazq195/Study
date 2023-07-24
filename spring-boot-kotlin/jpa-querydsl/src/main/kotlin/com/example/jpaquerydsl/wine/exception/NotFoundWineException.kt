package com.example.jpaquerydsl.wine.exception

import com.example.jpaquerydsl._common.exception.NotFoundEntityException
import com.example.jpaquerydsl.wine.domain.Wine

class NotFoundWineException : NotFoundEntityException(Wine::class.java)