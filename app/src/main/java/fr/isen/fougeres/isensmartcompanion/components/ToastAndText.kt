@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)


package fr.isen.fougeres.isensmartcompanion.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.ai.model
import fr.isen.fougeres.isensmartcompanion.backgroundColor
import fr.isen.fougeres.isensmartcompanion.roomdatabase.addInteractionToDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val sharedItems = mutableStateListOf<String>() // Shared list
val userRequest: MutableState<String> = mutableStateOf("")
var aiAnswer = ""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToastAndTextField(offsetY: Double) {

    val context = LocalContext.current
    val placeholderText = "Type your request here..."
    var temporaryText by remember { mutableStateOf("") }
    var mainOffsetY by remember { mutableDoubleStateOf(0.0) }
    mainOffsetY = offsetY

    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = offsetY.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Bottom)
    )

    {
        OutlinedTextField(singleLine = true,
            modifier = Modifier.background(backgroundColor),
            shape = RoundedCornerShape(24.dp),
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.White,
                errorBorderColor = Color.Blue,
                cursorColor = Color.Red
            ),

            placeholder = {
                Text(text = placeholderText, color = Color.White)
            },
            value = temporaryText,
            onValueChange = { newText ->
                temporaryText = newText  // Update the state when the user types
            })

        Button(modifier = Modifier.border(
            width = 2.dp, // Thickness of the border
            color = Color.Red, // Color of the border
            shape = ButtonDefaults.shape
        ), colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor, // Background color of the button
            contentColor = Color.White,  // Text or icon color inside the button
            disabledContainerColor = Color.Black, // Background color when disabled
            disabledContentColor = Color.Gray // Text color when disabled
        ), onClick = {
            if (temporaryText.isNotBlank()) {
                userRequest.value = temporaryText
                temporaryText = ""
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        aiAnswer = generateContentResponse(userRequest.value)
                        withContext(Dispatchers.Main) {
                            sharedItems.add(userRequest.value)
                            sharedItems.add(aiAnswer)
                        }
                    } catch (e: Exception) {
                        Log.e("Error", "Failed to generate content", e)
                    }
                }
                addInteractionToDatabase(
                    userRequest.value,
                    aiAnswer,
                    context
                    // BE CAREFUL, IT DOESN'T MAGICALLY ERASE THE PREVIOUS ADDITIONS
                    // TO THE DATABASE. YOU ALSO NEED TO CLEAN IT FOR DEV PURPOSES
                    // AND FIND A WAY TO GET THE LENGTH OF THE DB.
                )


            } else {
                val toast = Toast.makeText(
                    context, "Please write a request.", Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }) {
            Text(text = "Send request")
        }
    }
}

@Composable
fun PreviewToastAndTextField(offsetY: Double) {
    ToastAndTextField(offsetY)
}

suspend fun generateContentResponse(request: String): String {
    val response = model.generateContent(request)
    return response.text!!
}