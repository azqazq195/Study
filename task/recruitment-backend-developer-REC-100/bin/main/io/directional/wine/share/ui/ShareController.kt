package io.directional.wine.share.ui

import io.directional.wine.share.application.ShareService
import io.directional.wine.share.application.dto.ShareRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/share")
class ShareController(
    private val shareService: ShareService
) {
    @PostMapping
    fun create(
        @RequestBody shareRequest: ShareRequest
    ): ResponseEntity<Void> {
        shareService.create(shareRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody shareRequest: ShareRequest
    ): ResponseEntity<Void> {
        shareService.update(id, shareRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        shareService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}