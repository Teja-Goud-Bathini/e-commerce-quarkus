package com.tfl.framework.annotation


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Query(
    val value: String = "",
    val countQuery: String = "",
    val countProjection: String = "",
    val nativeQuery: Boolean = false,
    val name: String = "",
    val countName: String = ""
)
