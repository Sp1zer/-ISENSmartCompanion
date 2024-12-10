package fr.isen.fougeres.isensmartcompanion.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.backgroundColor
import fr.isen.fougeres.isensmartcompanion.screens.EventDetailActivity
import java.io.Serializable

var eventsList = emptyList<EventObject>()

data class EventObject(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val category: String
) : Serializable

@Composable
fun ProcessRetrievedEventList(events: List<EventObject>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        EventList(events)
    }
}

@Composable
private fun EventList(events: List<EventObject>) {
    events.forEach { event ->
        ShowEventObject(event)
    }
}

@Composable
fun ShowEventObject(event: EventObject) {
    val context = LocalContext.current

    StylishButton(24.toDouble(),
        4.0,
        backgroundColor,
        Color.Red,
        Color.White,
        Alignment.Center,
        text = event.title + " : " + event.date,
        12.0,
        onClick = {
            navigateToEventDetail(context, event)
        }
    )
}

private fun navigateToEventDetail(context: Context, event: EventObject) {
    val intent = Intent(context, EventDetailActivity::class.java).apply {
        putExtra("eventData", event)
    }
    context.startActivity(intent)
}

@Composable
fun EventDetails(event: EventObject) {
    // Create a list of pairs containing the label and the corresponding value
    val eventDetails = listOf(
        "ID" to event.id,
        "Title" to event.title,
        "Description" to event.description,
        "Date" to event.date,
        "Location" to event.location,
        "Category" to event.category
    )

    // Define specific sizes for each text
    val textSizes = listOf(
        16, // Size for ID
        30, // Size for Title
        20, // Size for Description
        25, // Size for Date
        20, // Size for Location
        25  // Size for Category
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Center align items horizontally
        modifier = Modifier.fillMaxWidth() // Make the column take the full width
    ) {
        // Iterate over the list and display each property using StylishText
        eventDetails.forEachIndexed { index, (label, value) ->
            StylishText(
                textColor = Color.White, // You can customize colors as needed
                alignment = TextAlign.Center,
                text = "$label: $value",
                padding = 8.0,
                fontSize = textSizes[index] // Assign specific size based on index
            )
        }
    }
}