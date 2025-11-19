package com.spotitworld.domain.repository

import com.spotitworld.domain.model.Difficulty
import com.spotitworld.domain.model.Hunt

interface ScavengerRepository {
    suspend fun generateHunt(
        locationDescription: String,
        itemCount: Int,
        difficulty: Difficulty
    ): Result<Hunt>
}
