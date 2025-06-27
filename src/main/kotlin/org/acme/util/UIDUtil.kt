package org.acme.util

import java.util.*
object UIDUtil {
    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}