package fr.isen.fougeres.isensmartcompanion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.backgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcessAgenda() {
    var newItem by remember { mutableStateOf(TextFieldValue("")) }
    var dateInput by remember { mutableStateOf(TextFieldValue("")) } // State for date input
    val agendaItems = remember { mutableStateListOf<Pair<String, String>>() } // Store items as pairs of (date, item)

    // Use a Column to layout the input fields and the agenda items
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Add some padding around the content
        verticalArrangement = Arrangement.Top, // Align items to the top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input field for date
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor),
            shape = RoundedCornerShape(24.dp),
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                errorBorderColor = Color.Blue,
                cursorColor = Color.Red
            ),
            value = dateInput,
            onValueChange = { dateInput = it },
            label = { Text("Enter Date", color = Color.White) } // Optional label
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input field for new agenda item
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor),
            shape = RoundedCornerShape(24.dp),
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                errorBorderColor = Color.Blue,
                cursorColor = Color.Red
            ),
            value = newItem,
            onValueChange = { newItem = it },
            label = { Text("Enter Agenda Item", color = Color.White) } // Optional label
        )

        Spacer(modifier = Modifier.height(8.dp))

        StylishButton(
            rounding = 24.0,
            outlineWidth = 4.0,
            backgroundColor = backgroundColor, // Set your desired background color
            outlineColor = Color.Red, // Set your desired outline color
            textColor = Color.White,
            alignment = Alignment.Center,
            text = "Add Agenda Item",
            padding = 12.0
        ) {
            // This block will be executed when the button is clicked
            if (newItem.text.isNotBlank() && dateInput.text.isNotBlank()) {
                agendaItems.add(Pair(dateInput.text, newItem.text)) // Store as a pair of (date, item)
                newItem = TextFieldValue("") // Clear the input field
                dateInput = TextFieldValue("") // Clear the date input field
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display the list of agenda items
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 84.dp) // Adjust this value based on your bottom bar height
        ) {
            items(agendaItems) { (date, item) -> // Destructure the pair
                AgendaItem(date, item) {
                    agendaItems.remove(Pair(date, item)) // Remove the item
                }
            }
        }
    }
}

@Composable
fun AgendaItem(date: String, item: String, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRemove() }
            .padding(8.dp), // Add some padding for better touch target
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$date: $item", color = Color.White, modifier = Modifier.weight(1f))
        Text(text = "Remove", color = Color.Red)
    }
}