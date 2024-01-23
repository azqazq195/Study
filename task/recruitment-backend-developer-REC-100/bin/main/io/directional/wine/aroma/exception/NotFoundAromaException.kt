package io.directional.wine.aroma.exception

import io.directional.wine._common.exception.NotFoundEntityException
import io.directional.wine.aroma.domain.Aroma

class NotFoundAromaException : NotFoundEntityException(Aroma::class.java)