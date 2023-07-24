package com.example.jpaquerydsl.grape.ui

import com.example.jpaquerydsl.grape.application.GrapeQueryService
import com.example.jpaquerydsl.grape.application.GrapeService
import com.example.jpaquerydsl.grape.application.dto.GrapeDetailsResponse
import com.example.jpaquerydsl.grape.application.dto.GrapeRequest
import com.example.jpaquerydsl.grape.application.dto.GrapeSearchParam
import com.example.jpaquerydsl.grape.application.dto.GrapeSummaryResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/grape")
class GrapeController(
    private val grapeService: GrapeService,
    private val grapeQueryService: GrapeQueryService
) {
    @PostMapping()
    fun create(
        @RequestBody grapeRequest: GrapeRequest,
    ): ResponseEntity<Void> {
        grapeService.create(grapeRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody grapeRequest: GrapeRequest,
    ): ResponseEntity<Void> {
        grapeService.update(id, grapeRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        grapeService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long
    ): ResponseEntity<GrapeDetailsResponse> {
        val response = grapeQueryService.getById(id)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/search")
    fun search(
        @ModelAttribute param: GrapeSearchParam,
        pageable: Pageable
    ): ResponseEntity<Page<GrapeSummaryResponse>> {
        val response = grapeQueryService.search(param, pageable)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}