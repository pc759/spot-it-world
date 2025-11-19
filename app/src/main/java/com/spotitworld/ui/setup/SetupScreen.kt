package com.spotitworld.ui.setup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spotitworld.domain.model.Difficulty

@Composable
fun SetupScreen(
    onStartHunt: (String, Difficulty, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var location by remember { mutableStateOf("") }
    var difficulty by remember { mutableStateOf(Difficulty.EXPLORER) }
    var itemCount by remember { mutableIntStateOf(9) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Spot It World",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Where are you exploring today?",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 1. Location Input
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location (e.g. Sheffield Park)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Difficulty Selector
        Text("Difficulty", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        DifficultySelector(current = difficulty, onSelect = { difficulty = it })

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Item Count (Grid Size)
        Text("Grid Size", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        ItemCountSelector(current = itemCount, onSelect = { itemCount = it })

        Spacer(modifier = Modifier.weight(1f))

        // 4. Go Button
        Button(
            onClick = { onStartHunt(location, difficulty, itemCount) },
            enabled = location.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Generate Hunt", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun DifficultySelector(
    current: Difficulty,
    onSelect: (Difficulty) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Difficulty.entries.forEach { level ->
            FilterChip(
                selected = current == level,
                onClick = { onSelect(level) },
                label = { Text(level.name.lowercase().capitalize()) }
            )
        }
    }
}

@Composable
fun ItemCountSelector(
    current: Int,
    onSelect: (Int) -> Unit
) {
    // Common bingo grid sizes
    val options = listOf(4, 9, 16) 
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        options.forEach { count ->
            FilterChip(
                selected = current == count,
                onClick = { onSelect(count) },
                label = { Text("$count Items") }
            )
        }
    }
}

// Extension helper
private fun String.capitalize() = replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
