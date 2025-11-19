package com.spotitworld.domain.model

data class ScavengerItem(
    val id: String,
    val name: String,
    val description: String,
    val emoji: String,
    val difficulty: Difficulty,
    var isFound: Boolean = false
)

enum class Difficulty {
    TODDLER,
    EXPLORER,
    EXPERT
}
