package com.example.jpaquerydsl.winery.exception

import com.example.jpaquerydsl._common.exception.NotFoundEntityException
import com.example.jpaquerydsl.winery.domain.Winery

class NotFoundWineryException : NotFoundEntityException(Winery::class.java)