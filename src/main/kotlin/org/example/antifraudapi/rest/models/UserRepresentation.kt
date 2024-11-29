package org.example.antifraudapi.rest.models

import org.springframework.hateoas.RepresentationModel

open class UserRepresentation(
    val id: String? = null,
    val name: String
): RepresentationModel<UserRepresentation>()