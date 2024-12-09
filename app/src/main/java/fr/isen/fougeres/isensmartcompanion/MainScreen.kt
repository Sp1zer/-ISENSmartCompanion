package fr.isen.fougeres.isensmartcompanion

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.components.PreviewCenteredCroppedImage
import fr.isen.fougeres.isensmartcompanion.components.PreviewToastAndTextField
import fr.isen.fougeres.isensmartcompanion.components.StylishBox
import fr.isen.fougeres.isensmartcompanion.components.StylishText
import fr.isen.fougeres.isensmartcompanion.components.sharedItems
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList

class MainScreen : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Set the current destination
        sharedViewModel.currentDestination = tabBarItemsList[0].title

        setContent {
            MainScreenContent(sharedViewModel)
        }
    }

    @Composable
    fun MainScreenContent(sharedViewModel: SharedViewModel) {
        // Implement specific behavior for Tab 0
        PreviewCenteredCroppedImage()
        StylishBox(
            rounding = 48.0,
            outlineWidth = 4.0,
            backgroundColor = backgroundColor,
            outlineColor = Color.Black,
            padding = 0.0,
            offset = 250,
            heightMin = 425,
            heightMax = 425
        ) {
            LazyColumn(
                modifier = Modifier.align(Alignment.TopCenter),
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
            ) {
                itemsIndexed(sharedItems) { index, item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = if (index % 2 == 0) {
                            Arrangement.Top
                        } else {
                            Arrangement.Bottom
                        }
                    ) {
                        StylishBox(
                            rounding = 24.0,
                            outlineWidth = 4.0,
                            backgroundColor = backgroundColor,
                            outlineColor = if (index % 2 == 0) Color.White else Color.Red,
                            padding = 12.0,
                            offset = 0,
                            heightMin = 50,
                            heightMax = null
                        ) {
                            StylishText(
                                textColor = Color.White,
                                alignment = TextAlign.Start,
                                text = item,
                                padding = 12.0
                            )
                        }
                    }
                }
            }
        }
        PreviewToastAndTextField(-110.0)
    }
}