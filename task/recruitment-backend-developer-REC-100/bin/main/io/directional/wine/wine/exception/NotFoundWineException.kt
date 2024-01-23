package io.directional.wine.wine.exception

import io.directional.wine._common.exception.NotFoundEntityException
import io.directional.wine.wine.domain.Wine

class NotFoundWineException : NotFoundEntityException(Wine::class.java)