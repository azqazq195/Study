package io.directional.wine.share.exception

import io.directional.wine._common.exception.NotFoundEntityException
import io.directional.wine.share.domain.Share

class NotFoundShareException : NotFoundEntityException(Share::class.java)