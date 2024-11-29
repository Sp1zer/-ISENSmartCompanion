package fr.isen.fougeres.isensmartcompanion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StylishBox(rounding: Double, outlineWidth: Double, backgroundColor: Color, outlineColor: Color, textColor: Color, arrangement: Arrangement.HorizontalOrVertical, text: String, padding: Double) {
    Box(
        modifier = Modifier
            .padding(padding.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(rounding.dp))
            .border(
                width = outlineWidth.dp, // Thickness of the border
                color = outlineColor, // Color of the border
            )
            .background(backgroundColor),

        )
    {
        Text(
            text = text,
            modifier = Modifier,
            color = textColor
        )
    }
}