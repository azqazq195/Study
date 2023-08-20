package com.example.jpaquerydsl.aroma.exception

import com.example.jpaquerydsl._common.exception.NotFoundEntityException
import com.example.jpaquerydsl.aroma.domain.Aroma

class NotFoundAromaException : NotFoundEntityException(Aroma::class.java)