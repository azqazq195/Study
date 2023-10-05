package io.directional.wine.aroma.ui

import io.directional.wine.aroma.application.AromaService
import io.directional.wine.aroma.application.dto.AromaRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/aroma")
class AromaController(
    private val aromaService: AromaService
) {
    @PostMapping
    fun create(
        @RequestBody aromaRequest: AromaRequest
    ): ResponseEntity<Void> {
        aromaService.create(aromaRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody aromaRequest: AromaRequest
    ): ResponseEntity<Void> {
        aromaService.update(id, aromaRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        aromaService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}