package org.acme.dto

data class ProductFilter(
    val name: String? = null,
    val brand: String? = null,
    val category: String? = null,
    val code: String? = null,
    val priceMin: Double? = null,
    val priceMax: Double? = null
)
