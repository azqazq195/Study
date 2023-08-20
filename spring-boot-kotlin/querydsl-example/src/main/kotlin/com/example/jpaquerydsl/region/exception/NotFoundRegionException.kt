package com.example.jpaquerydsl.region.exception

import com.example.jpaquerydsl._common.exception.NotFoundEntityException
import javax.swing.plaf.synth.Region

class NotFoundRegionException : NotFoundEntityException(Region::class.java)
