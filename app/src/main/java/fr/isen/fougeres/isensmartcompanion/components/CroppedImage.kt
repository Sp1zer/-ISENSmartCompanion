package fr.isen.fougeres.isensmartcompanion.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.fougeres.isensmartcompanion.R
import fr.isen.fougeres.isensmartcompanion.backgroundColor

@Preview(showBackground = true)

@Composable
fun CenteredCroppedImage()
{
    Box(
        modifier = Modifier
            .offset(y = 0.dp)
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