package com.spotitworld.ui.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotitworld.domain.model.Difficulty
import com.spotitworld.domain.model.Hunt
import com.spotitworld.domain.repository.ScavengerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class SetupViewModel(
    private val repository: ScavengerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SetupUiState())
    val uiState: StateFlow<SetupUiState> = _uiState.asStateFlow()

    fun onLocationChanged(location: String) {
        _uiState.value = _uiState.value.copy(location = location)
    }

    fun onDifficultyChanged(difficulty: Difficulty) {
        _uiState.value = _uiState.value.copy(difficulty = difficulty)
    }

    fun onItemCountChanged(count: Int) {
        _uiState.value = _uiState.value.copy(itemCount = count)
    }

    fun onGenerateClick() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            val currentState = _uiState.value
            val result = repository.generateHunt(
                locationDescription = currentState.location,
                itemCount = currentState.itemCount,
                difficulty = currentState.difficulty
            )
            
            result.onSuccess { items ->
                val newHunt = Hunt(
                    id = UUID.randomUUID().toString(),
                    locationName = currentState.location,
                    items = items,
                    createdAt = System.currentTimeMillis()
                )
                // TODO: Navigate to Game Screen with newHunt
                _uiState.value = _uiState.value.copy(isLoading = false, generatedHunt = newHunt)
            }.onFailure {
                _uiState.value = _uiState.value.copy(isLoading = false, error = it.message)
            }
        }
    }
}

data class SetupUiState(
    val location: String = "",
    val difficulty: Difficulty = Difficulty.EXPLORER,
    val itemCount: Int = 9,
    val isLoading: Boolean = false,
    val error: String? = null,
    val generatedHunt: Hunt? = null
)