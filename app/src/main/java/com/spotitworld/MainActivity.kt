package com.spotitworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.spotitworld.presentation.setup.SetupScreen
import com.spotitworld.presentation.setup.SetupViewModel
import com.spotitworld.data.repository.MockScavengerRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Manual Dependency Injection for now (keeping it simple)
        val repository = MockScavengerRepository()
        val viewModel = SetupViewModel(repository)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupScreen(viewModel = viewModel)
                }
            }
        }
    }
}
