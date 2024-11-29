package org.example.antifraudapi.rest.exceiption

import NoEntityResponse
import org.springframework.http.ResponseEntity

interface AntifraudApiAdvise {
    fun handleNoEntityAdvice(exception: EntityNotFoundException): ResponseEntity<NoEntityResponse>
}