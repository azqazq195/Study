package com.example.jpaquerydsl.importer.ui

import com.example.jpaquerydsl.importer.application.ImporterQueryService
import com.example.jpaquerydsl.importer.application.ImporterService
import com.example.jpaquerydsl.importer.application.dto.ImporterDetailsResponse
import com.example.jpaquerydsl.importer.application.dto.ImporterRequest
import com.example.jpaquerydsl.importer.application.dto.ImporterSearchParam
import com.example.jpaquerydsl.importer.application.dto.ImporterSummaryResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/importer")
class ImporterController(
    private val importerService: ImporterService,
    private val importerQueryService: ImporterQueryService,
) {
    @PostMapping
    fun create(
        @RequestBody importerRequest: ImporterRequest
    ): ResponseEntity<Void> {
        importerService.create(importerRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody importerRequest: ImporterRequest
    ): ResponseEntity<Void> {
        importerService.update(id, importerRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        importerService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long
    ): ResponseEntity<ImporterDetailsResponse> {
        val response = importerQueryService.getById(id)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/search")
    fun search(
        @ModelAttribute param: ImporterSearchParam,
        pageable: Pageable
    ): ResponseEntity<Page<ImporterSummaryResponse>> {
        val response = importerQueryService.search(param, pageable)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}