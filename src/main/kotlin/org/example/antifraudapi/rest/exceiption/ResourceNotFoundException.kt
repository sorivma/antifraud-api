package org.example.antifraudapi.rest.exceiption

import java.lang.RuntimeException

open class ResourceNotFoundException(
    val entity: String?,
    val id: String?,
) : RuntimeException()