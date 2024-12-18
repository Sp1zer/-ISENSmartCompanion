package fr.isen.fougeres.isensmartcompanion.roomdatabase

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.backgroundColor
import fr.isen.fougeres.isensmartcompanion.components.StylishBox
import fr.isen.fougeres.isensmartcompanion.components.StylishButton
import fr.isen.fougeres.isensmartcompanion.components.StylishText
import kotlinx.coroutines.async

@Composable
fun ShowHistory() {
    //cleanDatabase(LocalContext.current)

    val rounding = 24
    val context = LocalContext.current
    val userCount = remember { mutableIntStateOf(0) }


    LaunchedEffect(Unit) {
        val userCountDeferred = async { getUserCount(context) }
        userCount.intValue = userCountDeferred.await()
    }
    Column {
        Box(
            modifier = Modifier
                .offset(y = 25.dp)
                .heightIn(
                    min = 100.dp, max = 675.dp
                )
                .clip(RoundedCornerShape(48.dp))
                .fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier.align(Alignment.TopCenter), contentPadding = PaddingValues(
                    vertical = 16.dp, horizontal = 8.dp
                )
            ) {
                items(userCount.intValue) { index ->

                    val requestState = remember { mutableStateOf<String?>(null) }
                    val answerState = remember { mutableStateOf<String?>(null) }

                    LaunchedEffect(Unit) {
                        val requestDeferred =
                            async { getRequestForUser(userCount.intValue - (index), context) }
                        val answerDeferred =
                            async { getAnswerForUser(userCount.intValue - (index), context) }
                        requestState.value = requestDeferred.await()
                        answerState.value = answerDeferred.await()
                    }

                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(rounding / 2))
                            .fillMaxSize()
                            .background(Color.DarkGray)
                            .padding(12.dp)
                    ) {

                        StylishBox(
                            rounding = 24.0,
                            outlineWidth = 4.0,
                            backgroundColor = backgroundColor,
                            outlineColor = Color.Red,
                            padding = 24.0,
                            offset = 0,
                            heightMin = 50,
                            heightMax = null
                        ) {
                            StylishText(
                                Color.White,
                                TextAlign.Center,
                                text = ("Question : \n" + requestState.value),
                                12.0,
                                15
                            )
                        }

                        StylishBox(
                            rounding.toDouble(),
                            4.0,
                            backgroundColor,
                            Color.White,
                            12.0,
                            0,
                            100,
                            null,
                        ) {
                            StylishText(
                                Color.White,
                                TextAlign.Center,
                                text = ("Answer : \n" + answerState.value),
                                12.0,
                                15
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(rounding.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(rounding.dp * 2))

        StylishButton(
            rounding.toDouble(),
            4.0,
            backgroundColor,
            Color.Red,
            Color.White,
            Alignment.Center,
            text = " Clear History ?",
            12.0,
            onClick = {
                cleanDatabase(context)
                val toast = Toast.makeText(
                    context,
                    "Database Cleaned, please refresh the page to actualize the changes.",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        )
    }
}