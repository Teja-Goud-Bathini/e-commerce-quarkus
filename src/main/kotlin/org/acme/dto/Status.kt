package org.acme.dto

import io.vertx.core.eventbus.Message

data class Status(
    val code : Int?,
    val message: String
)
