@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.isensmartcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import android.widget.TextView
import android.widget.Toast

import com.example.isensmartcompanion.ui.theme.ISENSmartCompanionTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ISENSmartCompanionTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = backgroundColor) { innerPadding ->
                   null
                }
                PreviewCenteredCroppedImage()
                ToastAtBottomExample()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, color: Color) {
    Text(
        text = "Hello $name !",
        modifier = modifier,
        color = color
    )
}

@Preview(showBackground = true)

@Composable
fun GreetingPreview() {
    ISENSmartCompanionTheme {
        Greeting(
            "Android",
            color = Color.Black
        )
    }
}

@Composable
fun CenteredCroppedImage() {
    Box(
        modifier = Modifier
            .offset(y = 100.dp)
            .wrapContentSize() // Ensure the box adjusts to the image's size
            .background(backgroundColor) // Set the background color to white
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center), // Center the Column in the Box
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.isen), // Replace with your image resource
                contentDescription = "Centered Cropped Image",
                modifier = Modifier,
                contentScale = ContentScale.Fit // Fit the image within the Box without cropping
            )

            Text(
                text = "Smart Companion", // Replace with your desired text
                color = Color.White,
                fontSize = 30.sp,
                //fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun PreviewCenteredCroppedImage() {
    CenteredCroppedImage()
}

val backgroundColor = Color(0xFF282828)


@Composable
fun ToastAtBottomExample() {
    val context = LocalContext.current // Get the current context
    Box(
        modifier = Modifier.fillMaxSize().offset(y = 400.dp),
        contentAlignment = Alignment.Center
    )

    {
        Column(
            modifier = Modifier.align(Alignment.Center), // Center the Column in the Box
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {
            var text by remember{ mutableStateOf("Type your request here...") }

            OutlinedTextField(
                value = text, onValueChange = { newText -> text = newText},
                singleLine = true, modifier = Modifier.background(backgroundColor),
                textStyle = TextStyle(color = Color.White),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Red, // Color when focused
                    unfocusedBorderColor = Color.White, // Color when not focused
                    errorBorderColor = Color.Blue, // Color when in error state
                    cursorColor = Color.Red) // Cursor color
                    )

            Button(

                onClick = {
                    val toastView = toast.view
                    val textView = toastView?.findViewById<TextView>(android.R.id.message)
                    val toast = Toast.makeText(
                        context,"Your request has been sent and is now being treated by our smart companion.",
                        Toast.LENGTH_LONG
                        textView?.setTextColor(Color.WHITE)

                    )

                    toast.show()
                }
            )

            {
                Text(text = "Send request")
            }
        }
    }
}

@Preview
@Composable
fun PreviewToastAtBottomExample() {
    ToastAtBottomExample()
}