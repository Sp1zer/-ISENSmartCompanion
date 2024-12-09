package fr.isen.fougeres.isensmartcompanion

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList

class AgendaScreen : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set the current destination
        sharedViewModel.currentDestination = tabBarItemsList[2].title

        setContent {
            AgendaScreenContent(sharedViewModel)
        }
    }

    @Composable
    fun AgendaScreenContent(sharedViewModel: SharedViewModel) {
        // Display content for AgendaScreen
        Text(text = "Bisous", color = Color.White)
    }
}