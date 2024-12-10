package fr.isen.fougeres.isensmartcompanion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StylishBox(
    rounding: Double,
    outlineWidth: Double,
    backgroundColor: Color,
    outlineColor: Color,
    padding: Double,
    offset : Int,
    heightMin : Int,
    heightMax : Int?,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .offset(y = offset.dp)
            .fillMaxWidth()
            .padding(padding.dp)
            .clip(RoundedCornerShape(rounding.dp))
            .border(
                width = outlineWidth.dp, // Thickness of the border
                color = outlineColor, // Color of the border
                shape = RoundedCornerShape(rounding.dp)
            )
            .background(backgroundColor)
            .then(
                heightMax?.let { maxHeight ->
                    Modifier.heightIn(min = heightMin.dp, max = maxHeight.dp)
                } ?: Modifier
            ),
        )
    {
        content()
    }
}

@Composable
fun StylishButton(
    rounding: Double,
    outlineWidth: Double,
    backgroundColor: Color,
    outlineColor: Color,
    textColor: Color,
    alignment: Alignment,
    text: String,
    padding: Double,
    onClick: @Composable () -> Unit // Keep onClick as a Composable
) {
    var clicked by remember { mutableStateOf(false) } // State to track if the button was clicked

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.dp)
            .clip(RoundedCornerShape(rounding.dp))
            .border(
                width = outlineWidth.dp,
                color = outlineColor,
                shape = RoundedCornerShape(rounding.dp)
            )
            .background(backgroundColor)
            .clickable {
                clicked = true // Set clicked to true when the button is clicked
            }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .align(alignment)
                .padding(padding.dp),
            color = textColor
        )

        // Show the onClick composable when clicked
        if (clicked) {
            Box(modifier = Modifier.align(Alignment.Center)) {
                onClick() // Call the onClick composable
                // Reset clicked state after the onClick is executed
                clicked = false
            }
        }
    }
}

@Composable
fun StylishText(
    textColor: Color,
    alignment: TextAlign,
    text: String,
    padding: Double,
    fontSize: Int?
)
{
    fontSize?.let {
        Text(
        text = text,
        modifier = Modifier
            .padding(padding.dp),
        textAlign = alignment,
        style = TextStyle(color = textColor),
        fontSize = it.sp
        )
    }
}