package com.spotitworld.data.repository

import com.spotitworld.domain.model.Difficulty
import com.spotitworld.domain.model.ScavengerItem
import com.spotitworld.domain.repository.ScavengerRepository
import kotlinx.coroutines.delay
import java.util.UUID

/**
 * A fake repository to allow UI development before the AI backend is ready.
 */
class MockScavengerRepository : ScavengerRepository {

    override suspend fun generateHunt(
        locationDescription: String,
        itemCount: Int,
        difficulty: Difficulty
    ): Result<List<ScavengerItem>> {
        // Simulate network delay
        delay(1500)

        val mockItems = when (difficulty) {
            Difficulty.TODDLER -> listOf(
                createItem("Muddy Puddle", "Find a puddle to jump in!", "💧", difficulty),
                createItem("Red Leaf", "A fallen leaf that is red.", "🍁", difficulty),
                createItem("Big Stick", "A stick larger than your arm.", "🪵", difficulty),
                createItem("Yellow Flower", "Any small yellow flower.", "🌼", difficulty)
            )
            Difficulty.EXPLORER -> listOf(
                createItem("Oak Tree", "A tree with lobed leaves.", "🌳", difficulty),
                createItem("Squirrel", "A grey or red squirrel.", "🐿️", difficulty),
                createItem("Moss", "Green soft plant on a tree or rock.", "🌿", difficulty),
                createItem("Pinecone", "A seed from a pine tree.", "🌲", difficulty)
            )
            Difficulty.EXPERT -> listOf(
                createItem("Lichen", "Symbiotic fungus on rocks.", "🪨", difficulty),
                createItem("Birch Bark", "Peeling white bark.", "⚪", difficulty),
                createItem("Robin", "Erithacus rubecula", "🐦", difficulty),
                createItem("Fungi", "Wild mushroom (Don't touch!)", "🍄", difficulty)
            )
        }

        // Return a sublist based on requested count
        return Result.success(mockItems.take(itemCount))
    }

    override suspend fun saveHunt(items: List<ScavengerItem>) {
        // No-op for mock
        println("Mock: Saved ${items.size} items.")
    }

    private fun createItem(name: String, desc: String, emoji: String, diff: Difficulty) =
        ScavengerItem(
            id = UUID.randomUUID().toString(),
            name = name,
            description = desc,
            emoji = emoji,
            difficulty = diff
        )
}
