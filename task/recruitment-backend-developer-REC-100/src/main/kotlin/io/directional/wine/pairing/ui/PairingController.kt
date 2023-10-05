package io.directional.wine.pairing.ui

import io.directional.wine.pairing.application.PairingService
import io.directional.wine.pairing.application.dto.PairingRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pairing")
class PairingController(
    private val pairingService: PairingService
) {
    @PostMapping
    fun create(
        @RequestBody pairingRequest: PairingRequest
    ): ResponseEntity<Void> {
        pairingService.create(pairingRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody pairingRequest: PairingRequest
    ): ResponseEntity<Void> {
        pairingService.update(id, pairingRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable id: Long,
    ): ResponseEntity<Void> {
        pairingService.delete(id)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
}