package fr.isen.fougeres.isensmartcompanion.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class EventObject(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val category: String
)

@Composable
fun ShowEventObject(
    event:EventObject
) {
    Button(
        onClick = {

        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        )
        {
            Text(
                text = event.id,
                color = Color.White,
                fontSize = 30.sp
            )
            Text(
                text = event.title,
                color = Color.White,
                fontSize = 20.sp
            )
            Text(
                text = event.description,
                color = Color.White,
                fontSize = 10.sp
            )
            Text(
                text = event.date,
                color = Color.White,
                fontSize = 15.sp
            )
            Text(
                text = event.location,
                color = Color.White,
                fontSize = 15.sp
            )
            Text(
                text = event.category,
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }

}