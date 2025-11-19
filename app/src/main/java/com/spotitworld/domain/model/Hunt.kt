package com.spotitworld.domain.model

import java.util.UUID

/**
 * Represents a specific Scavenger Hunt session.
 * 
 * @property id Unique identifier (crucial for future sharing/multiplayer).
 * @property locationName The user-entered string (e.g. "Sheffield Park").
 * @property items The list of items generated for this hunt.
 */
data class Hunt(
    val id: String = UUID.randomUUID().toString(),
    val locationName: String,
    val difficulty: Difficulty,
    val items: List<ScavengerItem>,
    val createdAt: Long = System.currentTimeMillis()
)
