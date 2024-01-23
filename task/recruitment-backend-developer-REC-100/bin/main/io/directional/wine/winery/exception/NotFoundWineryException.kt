package io.directional.wine.winery.exception

import io.directional.wine._common.exception.NotFoundEntityException
import io.directional.wine.winery.domain.Winery

class NotFoundWineryException : NotFoundEntityException(Winery::class.java)