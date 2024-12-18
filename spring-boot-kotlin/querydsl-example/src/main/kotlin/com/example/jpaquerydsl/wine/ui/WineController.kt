package com.example.jpaquerydsl.wine.ui

import com.example.jpaquerydsl.wine.application.WineQueryService
import com.example.jpaquerydsl.wine.application.WineService
import com.example.jpaquerydsl.wine.application.dto.WineDetailsResponse
import com.example.jpaquerydsl.wine.application.dto.WineRequest
import com.example.jpaquerydsl.wine.application.dto.WineSearchParam
import com.example.jpaquerydsl.wine.application.dto.WineSummaryResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/wine")
class WineController(
    private val wineService: WineService,
    private val wineQueryService: WineQueryService
) {
    @PostMapping
    fun create(
        @RequestBody wineRequest: WineRequest
    ): ResponseEntity<Void> {
        wineService.create(wineRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody wineRequest: WineRequest
    ): ResponseEntity<Void> {
        wineService.update(id, wineRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        wineService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long
    ): ResponseEntity<WineDetailsResponse> {
        val response = wineQueryService.getById(id)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/search")
    fun search(
        @ModelAttribute param: WineSearchParam,
        pageable: Pageable
    ): ResponseEntity<Page<WineSummaryResponse>> {
        val response = wineQueryService.search(param, pageable)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}