package org.example.antifraudapi.rest.exceiption

interface EntityNotFoundException {
    val message: String?
    val id: String
    val entity: String?
}