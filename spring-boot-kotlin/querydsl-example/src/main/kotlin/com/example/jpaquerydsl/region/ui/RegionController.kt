package com.example.jpaquerydsl.region.ui

import com.example.jpaquerydsl.region.application.RegionQueryService
import com.example.jpaquerydsl.region.application.RegionService
import com.example.jpaquerydsl.region.application.dto.RegionDetailsResponse
import com.example.jpaquerydsl.region.application.dto.RegionRequest
import com.example.jpaquerydsl.region.application.dto.RegionSearchParam
import com.example.jpaquerydsl.region.application.dto.RegionSummaryResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/region")
class RegionController(
    private val regionService: RegionService,
    private val regionQueryService: RegionQueryService
) {
    @PostMapping()
    fun create(
        @RequestBody regionRequest: RegionRequest
    ): ResponseEntity<Void> {
        regionService.create(regionRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody regionRequest: RegionRequest
    ): ResponseEntity<Void> {
        regionService.update(id, regionRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        regionService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long
    ): ResponseEntity<RegionDetailsResponse> {
        val response = regionQueryService.getById(id)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/search")
    fun search(
        @ModelAttribute param: RegionSearchParam,
        pageable: Pageable
    ): ResponseEntity<Page<RegionSummaryResponse>> {
        val response = regionQueryService.search(param, pageable)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}