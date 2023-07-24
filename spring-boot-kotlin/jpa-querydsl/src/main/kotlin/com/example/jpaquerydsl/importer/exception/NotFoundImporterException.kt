package com.example.jpaquerydsl.importer.exception

import com.example.jpaquerydsl._common.exception.NotFoundEntityException
import com.example.jpaquerydsl.importer.domain.Importer

class NotFoundImporterException : NotFoundEntityException(Importer::class.java)