@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)


package fr.isen.fougeres.isensmartcompanion.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.backgroundColor

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ToastAndTextField(offsetY: Double) {

    val context = LocalContext.current
    val placeholderText = "Type your request here..."
    var temporaryText by remember { mutableStateOf("") }
    var userRequest by remember { mutableStateOf("") }
    var mainOffsetY by remember { mutableDoubleStateOf(0.0) }
    mainOffsetY = offsetY

    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = offsetY.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )

    {
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .background(backgroundColor),
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors
                (
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.White,
                errorBorderColor = Color.Blue,
                cursorColor = Color.Red
            ),

            placeholder =
            {
                Text(text = placeholderText, color = Color.White)
            },
            value = temporaryText,
            onValueChange =
            { newText ->
                temporaryText = newText  // Update the state when the user types
            })

        Button(
            onClick =
            {
                if (temporaryText.isNotBlank()) {
                    userRequest = temporaryText
                    temporaryText = ""
                    val toast = Toast.makeText(
                        context,
                        "Your request has been sent and is now being treated by our smart companion.",
                        Toast.LENGTH_LONG
                    )
                    toast.show()


                } else {
                    val toast = Toast.makeText(
                        context, "Please write a request.",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            })
        {
            Text(text = "Send request")
        }
    }
}

@Preview
@Composable
fun PreviewToastAndTextField(offsetY: Double) {
    ToastAndTextField(offsetY)
}