package com.spotitworld.data.repository

import com.spotitworld.domain.model.Difficulty
import com.spotitworld.domain.model.Hunt
import com.spotitworld.domain.model.ScavengerItem
import com.spotitworld.domain.repository.ScavengerRepository
import java.util.UUID

class MockScavengerRepository : ScavengerRepository {
    
    override suspend fun generateHunt(
        locationDescription: String,
        itemCount: Int,
        difficulty: Difficulty
    ): Result<Hunt> {
        // Simulate network delay
        kotlinx.coroutines.delay(1000)
        
        val mockItems = List(itemCount) { index ->
            ScavengerItem(
                id = UUID.randomUUID().toString(),
                name = "Mock Item $index",
                description = "Something you might find at $locationDescription",
                emoji = "🔍",
                difficulty = difficulty
            )
        }
        
        return Result.success(
            Hunt(
                locationName = locationDescription,
                difficulty = difficulty,
                items = mockItems
            )
        )
    }
}
