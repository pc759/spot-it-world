package com.spotitworld.domain.repository

import com.spotitworld.domain.model.Difficulty
import com.spotitworld.domain.model.ScavengerItem

interface ScavengerRepository {
    /**
     * Generates a new hunt based on the user's parameters.
     */
    suspend fun generateHunt(
        locationDescription: String,
        itemCount: Int,
        difficulty: Difficulty
    ): Result<List<ScavengerItem>>

    /**
     * Saves a hunt to local storage (Room).
     */
    suspend fun saveHunt(items: List<ScavengerItem>)
}
