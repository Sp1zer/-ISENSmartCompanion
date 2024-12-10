package fr.isen.fougeres.isensmartcompanion.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.components.ProcessAgenda
import fr.isen.fougeres.isensmartcompanion.SharedViewModel
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList

class AgendaScreen : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set the current destination
        sharedViewModel.currentDestination = tabBarItemsList[2].title

        setContent {
            AgendaScreenContent()
        }
    }

    @Composable
    fun AgendaScreenContent() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Add padding to the entire column
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp)) // Add space between header and agenda
            ProcessAgenda()
        }
    }
}

