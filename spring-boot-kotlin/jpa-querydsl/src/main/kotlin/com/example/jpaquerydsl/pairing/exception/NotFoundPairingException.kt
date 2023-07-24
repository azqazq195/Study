package com.example.jpaquerydsl.pairing.exception

import com.example.jpaquerydsl._common.exception.NotFoundEntityException
import com.example.jpaquerydsl.pairing.domain.Pairing

class NotFoundPairingException : NotFoundEntityException(Pairing::class.java)