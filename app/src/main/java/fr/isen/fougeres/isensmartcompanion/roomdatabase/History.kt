package fr.isen.fougeres.isensmartcompanion.roomdatabase

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.backgroundColor
import fr.isen.fougeres.isensmartcompanion.components.StylishBox
import fr.isen.fougeres.isensmartcompanion.components.sharedItems


@Composable
fun ShowHistory() {
    Box(
        modifier = Modifier
            .offset(y = 25.dp)
            .heightIn(
                min = 100.dp, max = 775.dp
            )
            .clip(RoundedCornerShape(48.dp))
            .fillMaxSize(),
    ) {

        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter),
            contentPadding = PaddingValues(
                vertical = 16.dp,
                horizontal = 8.dp
            )
        )
        {
            items(5) { index ->
                StylishBox(24.0, 4.0, backgroundColor, Color.Red, Color.White, Arrangement.Center, "This is a test.", 12.0)
            }
        }






        LazyColumn(
            modifier = Modifier.align(Alignment.TopCenter), contentPadding = PaddingValues(
                vertical = 16.dp, horizontal = 8.dp
            )
        ) {
            itemsIndexed(sharedItems) { index, item ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = if (index % 2 == 0) {
                        Arrangement.Start
                    } else {
                        Arrangement.End
                    }
                ) {
                    Text(
                        text = item, modifier = Modifier
                            .border(
                                width = 4.dp, // Border width
                                color = if (index % 2 == 0) Color.White else Color.Red, // Border color
                                shape = RoundedCornerShape(24.dp)
                            )
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(24.dp) // Rounded corners
                            )
                            .padding(
                                horizontal = 12.dp, vertical = 12.dp
                            ), color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun UserRow(user: User) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            BasicText(text = "First Name: ${user.request ?: "N/A"}")
            BasicText(text = "Last Name: ${user.answer ?: "N/A"}")
        }
    }
}