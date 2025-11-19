package com.spotitworld.domain.model

import java.util.UUID

data class Hunt(
    val id: String = UUID.randomUUID().toString(),
    val locationName: String,
    val difficulty: Difficulty,
    val items: List<ScavengerItem>,
    val createdAt: Long = System.currentTimeMillis()
)
