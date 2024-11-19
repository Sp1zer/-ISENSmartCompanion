package com.example.isensmartcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.isensmartcompanion.ui.theme.ISENSmartCompanionTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize() // Fill the entire screen
                    .background(Color.hsl(0f,0f,0f))
            )
            ISENSmartCompanionTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Black) { innerPadding ->
                    Greeting(
                        name = "World",
                        modifier = Modifier.padding(innerPadding),
                        color = Color.White
                    )
                }
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
fun DrawableImage() {
    Image(
        painter = painterResource(id = R.drawable.ISEN), // Replace with your image resource
        contentDescription = "Description of the image",
        modifier = Modifier.size(200.dp), // Optional size modifier
        contentScale = ContentScale.Crop // Optional scaling
    )
}

@Preview
@Composable
fun PreviewDrawableImage() {
    DrawableImage()
}