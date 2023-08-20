package com.example.jpaquerydsl.share.exception

import com.example.jpaquerydsl._common.exception.NotFoundEntityException
import com.example.jpaquerydsl.share.domain.Share

class NotFoundShareException : NotFoundEntityException(Share::class.java)