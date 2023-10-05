package io.directional.wine.grape.exception

import io.directional.wine._common.exception.NotFoundEntityException
import io.directional.wine.grape.domain.Grape

class NotFoundGrapeException : NotFoundEntityException(Grape::class.java)
