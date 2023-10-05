package io.directional.wine.importer.exception

import io.directional.wine._common.exception.NotFoundEntityException
import io.directional.wine.importer.domain.Importer

class NotFoundImporterException : NotFoundEntityException(Importer::class.java)