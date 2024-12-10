package fr.isen.fougeres.isensmartcompanion.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import fr.isen.fougeres.isensmartcompanion.SharedViewModel
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList
import fr.isen.fougeres.isensmartcompanion.roomdatabase.ShowHistory

class HistoryScreen : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set the current destination
        sharedViewModel.currentDestination = tabBarItemsList[3].title

        setContent {
            HistoryScreenContent()
        }
    }

    @Composable
    fun HistoryScreenContent() {
        ShowHistory() // Call a method to show history
    }
}