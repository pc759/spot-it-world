package com.spotitworld.presentation.setup

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spotitworld.domain.model.Difficulty

@Composable
fun SetupScreen(
    viewModel: SetupViewModel,
    modifier: Modifier = Modifier
) {
    var location by remember { mutableStateOf("") }
    var difficulty by remember { mutableStateOf(Difficulty.EXPLORER) }
    var itemCount by remember { mutableStateOf(9) } // Default to 3x3 grid

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Project Magpie",
            style = MaterialTheme.typography.headlineLarge
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        // Location Input
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Where are you?") },
            placeholder = { Text("e.g. Sheffield Park") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Difficulty Selection
        Text("Difficulty: ${difficulty.name}")
        Slider(
            value = difficulty.ordinal.toFloat(),
            onValueChange = { 
                val index = it.toInt().coerceIn(0, Difficulty.values().lastIndex)
                difficulty = Difficulty.values()[index]
            },
            valueRange = 0f..Difficulty.values().lastIndex.toFloat(),
            steps = Difficulty.values().size - 2
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Item Count Selection
        Text("Items to find: $itemCount")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf(4, 9, 16).forEach { count ->
                FilterChip(
                    selected = itemCount == count,
                    onClick = { itemCount = count },
                    label = { Text("$count") }
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Action Button
        Button(
            onClick = { viewModel.generateHunt(location, difficulty, itemCount) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = location.isNotBlank()
        ) {
            Text("Let's Go!")
        }
    }
}
