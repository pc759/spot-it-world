package com.spotitworld.domain.model

enum class Difficulty {
    TODDLER,    // Simple colors, big objects
    EXPLORER,   // Common nature items (Oak leaf, specific bird)
    EXPERT      // Scientific names, rare finds
}

data class ScavengerItem(
    val id: String,
    val name: String,
    val description: String,
    val emoji: String,
    val difficulty: Difficulty,
    val isFound: Boolean = false
)
