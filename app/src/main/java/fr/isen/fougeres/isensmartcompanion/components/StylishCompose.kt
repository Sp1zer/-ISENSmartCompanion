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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

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

@Composable // Needs to be modified so it takes similar parameters as StylishBox.
fun StylishButton(
    rounding: Double,
    outlineWidth: Double,
    backgroundColor: Color,
    outlineColor: Color,
    textColor: Color,
    alignment: Alignment,
    text: String,
    padding: Double,
    onClick: () -> Unit // onClick function to execute when the button is clicked
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.dp)
            .clip(RoundedCornerShape(rounding.dp))
            .border(
                width = outlineWidth.dp, // Thickness of the border
                color = outlineColor, // Color of the border
                shape = RoundedCornerShape(rounding.dp)
            )
            .background(backgroundColor)
            .clickable(onClick = onClick) // Make the box clickable with the onClick event
    ) {
        Text(
            text = text,
            modifier = Modifier
                .align(alignment) // Align the text based on the given alignment
                .padding(padding.dp),
            color = textColor
        )
    }
}

@Composable
fun StylishText(
    textColor: Color,
    alignment: TextAlign,
    text: String,
    padding: Double,
)
{
    Text(
        text = text,
        modifier = Modifier
            .padding(padding.dp),
        textAlign = alignment,
        style = TextStyle(color = textColor)
    )
}