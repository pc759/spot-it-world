package com.spotitworld.presentation.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotitworld.domain.model.Difficulty
import com.spotitworld.domain.repository.ScavengerRepository
import kotlinx.coroutines.launch

class SetupViewModel(
    private val repository: ScavengerRepository
) : ViewModel() {

    fun generateHunt(location: String, difficulty: Difficulty, itemCount: Int) {
        viewModelScope.launch {
            val result = repository.generateHunt(location, itemCount, difficulty)
            // TODO: Handle result and navigate to Game Screen
            println("Generated Hunt: $result")
        }
    }
}
