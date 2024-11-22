package fr.isen.fougeres.isensmartcompanion.components

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.fougeres.isensmartcompanion.EventScreen


data class EventObject(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val category: String
)

@Composable
fun ProcessRetrievedEventList(events: List<EventObject>) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        // Iterate over the list of events and display each event using ShowEventObject
        events.forEach { event ->
            ShowEventObject(event)  // Passing each EventObject to ShowEventObject
        }
    }
}


@Composable
fun ShowEventObject(
    event: EventObject
) {
    val context = LocalContext.current
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray
        ),
        onClick = {
            val intent = Intent(context, EventScreen::class.java)/*.apply
            {
                putExtra("event",event)
            }*/

            context.startActivity((intent))
        },
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = event.title,
                color = Color.White,
                fontSize = 30.sp
            )
            Text(
                text = event.description,
                color = Color.White,
                fontSize = 10.sp
            )
            Text(
                text = event.date,
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                text = event.location,
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }


}