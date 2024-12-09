package fr.isen.fougeres.isensmartcompanion

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.components.ProcessRetrievedEventList
import fr.isen.fougeres.isensmartcompanion.components.eventsList
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList

class EventScreen : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set the current destination
        sharedViewModel.currentDestination = tabBarItemsList[1].title

        setContent {
            EventScreenContent(sharedViewModel)
        }
    }

    @Composable
    fun EventScreenContent(sharedViewModel: SharedViewModel) {
        Log.d("ZIMBABWE", "ASKIP C'EST CENSÉ MARCHER")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 25.dp)
                .heightIn(max = 750.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(100.dp)
            ) {
                item {
                    ProcessRetrievedEventList(eventsList)
                }
            }
        }
    }
}