package io.directional.wine.winery.ui

import io.directional.wine.winery.application.WineryQueryService
import io.directional.wine.winery.application.WineryService
import io.directional.wine.winery.application.dto.WineryDetailsResponse
import io.directional.wine.winery.application.dto.WineryRequest
import io.directional.wine.winery.application.dto.WinerySearchParam
import io.directional.wine.winery.application.dto.WinerySummaryResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/winery")
class WineryController(
    private val wineryService: WineryService,
    private val wineryQueryService: WineryQueryService
) {
    @PostMapping
    fun create(
        @RequestBody wineryRequest: WineryRequest
    ): ResponseEntity<Void> {
        wineryService.create(wineryRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody wineryRequest: WineryRequest
    ): ResponseEntity<Void> {
        wineryService.update(id, wineryRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        wineryService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long
    ): ResponseEntity<WineryDetailsResponse> {
        val response = wineryQueryService.getById(id)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/search")
    fun search(
        @ModelAttribute param: WinerySearchParam,
        pageable: Pageable
    ): ResponseEntity<Page<WinerySummaryResponse>> {
        val response = wineryQueryService.search(param, pageable)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}