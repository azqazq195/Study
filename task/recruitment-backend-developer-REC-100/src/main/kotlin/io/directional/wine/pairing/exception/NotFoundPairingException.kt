package io.directional.wine.pairing.exception

import io.directional.wine._common.exception.NotFoundEntityException
import io.directional.wine.pairing.domain.Pairing

class NotFoundPairingException : NotFoundEntityException(Pairing::class.java)