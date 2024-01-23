package io.directional.wine.region.exception

import io.directional.wine._common.exception.NotFoundEntityException
import javax.swing.plaf.synth.Region

class NotFoundRegionException : NotFoundEntityException(Region::class.java)
