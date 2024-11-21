@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package fr.isen.fougeres.isensmartcompanion

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

import android.widget.Toast

import fr.isen.fougeres.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

val backgroundColor = Color(0xFF282828)

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = backgroundColor)
                {
                    innerPadding -> null
                }
                PreviewCenteredCroppedImage()
                PreviewToastAndTextField()
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, color: Color)
{
    Text(
        text = "Hello $name !",
        modifier = modifier,
        color = color
    )
}*/

@Preview(showBackground = true)

/*@Composable
fun GreetingPreview() {
    ISENSmartCompanionTheme{
        Greeting(
            "Android",
            color = Color.Black)
    }
}*/

@Composable
fun CenteredCroppedImage() {
    Box(
        modifier = Modifier
            .offset(y = 100.dp)
            .wrapContentSize()
            .background(backgroundColor))
    {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Image(
                painter = painterResource(id = R.drawable.isen),
                contentDescription = "Centered Cropped Image",
                modifier = Modifier,
                contentScale = ContentScale.Fit)

            Text(
                text = "Smart Companion",
                color = Color.White,
                fontSize = 30.sp)
        }
    }
}

@Preview
@Composable
fun PreviewCenteredCroppedImage() {
    CenteredCroppedImage()
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun ToastAndTextField() {
    val context = LocalContext.current // Get the current context
    var placeholderText = "Type your request here..."
    var temporaryText by remember{ mutableStateOf("") }
    var userRequest by remember{ mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .offset(y = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)

    {
        OutlinedTextField(
            singleLine = true,
            modifier = Modifier
                .background(backgroundColor),
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors
                (focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.White,
                errorBorderColor = Color.Blue,
                cursorColor = Color.Red),

            placeholder =
            {
                Text(text = placeholderText, color = Color.White)
            },
            value = temporaryText,
            onValueChange =
            {
                newText -> temporaryText = newText  // Update the state when the user types
            })

        Button(
            onClick =
            {
                if(temporaryText.isNotBlank())
                {
                    userRequest = temporaryText
                    temporaryText = ""
                    val toast = Toast.makeText(
                        context,"Your request has been sent and is now being treated by our smart companion.",
                        Toast.LENGTH_LONG)
                    toast.show()
                }
                else
                {
                    val toast = Toast.makeText(
                        context,"Please write a request.",
                        Toast.LENGTH_SHORT)
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
fun PreviewToastAndTextField()
{
    ToastAndTextField()
}