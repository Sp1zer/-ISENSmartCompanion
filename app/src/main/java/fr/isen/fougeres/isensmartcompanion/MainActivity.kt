package fr.isen.fougeres.isensmartcompanion

/*import fr.isen.fougeres.isensmartcompanion.components.EventObject
import fr.isen.fougeres.isensmartcompanion.components.PreviewCenteredCroppedImage
import fr.isen.fougeres.isensmartcompanion.components.PreviewToastAndTextField
import fr.isen.fougeres.isensmartcompanion.components.ShowEventObject
import fr.isen.fougeres.isensmartcompanion.components.TabView
import fr.isen.fougeres.isensmartcompanion.components.TabBarItem
import fr.isen.fougeres.isensmartcompanion.components.eventObjectList*/


//Je sais que vous avez dit pas de wildcard, mais c'est plus pratique et pour l'instant
// je n'ai eu aucun problème à cause de ça.

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.fougeres.isensmartcompanion.api.RetrofitClient
import fr.isen.fougeres.isensmartcompanion.components.EventObject
import fr.isen.fougeres.isensmartcompanion.components.PreviewCenteredCroppedImage
import fr.isen.fougeres.isensmartcompanion.components.PreviewToastAndTextField
import fr.isen.fougeres.isensmartcompanion.components.ProcessRetrievedEventList
import fr.isen.fougeres.isensmartcompanion.components.TabView
import fr.isen.fougeres.isensmartcompanion.components.sharedItems
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList
import fr.isen.fougeres.isensmartcompanion.ui.theme.ISENSmartCompanionTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val backgroundColor = Color(0xFF282828)

class MainActivity : ComponentActivity() {

    var eventsList = emptyList<EventObject>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFirebaseEvent()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            ISENSmartCompanionTheme {
                Scaffold(
                    bottomBar = { TabView(tabBarItemsList, navController) },
                    containerColor = backgroundColor,
                ) {
                    NavHost(
                        navController = navController, startDestination = tabBarItemsList[0].title
                    ) {
                        composable(tabBarItemsList[0].title) {
                            PreviewCenteredCroppedImage()
                            Box(
                                modifier = Modifier
                                    .offset(y = 175.dp) // Start 200.dp from the top
                                    .heightIn(
                                        min = 100.dp, max = 500.dp
                                    )
                                    .border(
                                        width = 4.dp, // Border width
                                        color = Color.Black, // Border color
                                        shape = RoundedCornerShape(48.dp)
                                    )
                                    .clip(RoundedCornerShape(48.dp))
                                    .fillMaxSize() // Box takes up the full width of the parent
                                    .background(backgroundColor),
                                contentAlignment = Alignment.Center
                            )
                            {
                                LazyColumn(
                                    modifier = Modifier.align(Alignment.TopCenter),
                                    contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
                                )
                                {
                                    itemsIndexed(sharedItems) { index, item ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp),
                                            horizontalArrangement = if (index % 2 == 0) {
                                                Arrangement.Start
                                            } else {
                                                Arrangement.End
                                            }
                                        )
                                        {
                                            Text(
                                                text = item,
                                                modifier = Modifier
                                                    .border(
                                                        width = 4.dp, // Border width
                                                        color = if (index % 2 == 0) Color.White else Color.Red, // Border color
                                                        shape = RoundedCornerShape(24.dp)
                                                    )
                                                    .background(
                                                        color = backgroundColor,
                                                        shape = RoundedCornerShape(24.dp) // Rounded corners
                                                    )
                                                    .padding(
                                                        horizontal = 12.dp,
                                                        vertical = 12.dp
                                                    ),
                                                color = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                            PreviewToastAndTextField(-110.0)
                        }
                        composable(tabBarItemsList[1].title) {
                            Box(
                                modifier = Modifier
                                    .offset(y = 50.dp)
                                    .heightIn(
                                        max = 737.5.dp
                                    )

                                    .fillMaxSize() // Fill the parent container (e.g., screen or parent Box)
                            ) {
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(100.dp)
                                ) {
                                    item {
                                        ProcessRetrievedEventList(eventsList)
                                        //EventScreen(::navigateToDetail)
                                    }
                                }
                            }
                        }
                        composable(tabBarItemsList[2].title) {
                            Text(tabBarItemsList[2].title)
                        }
                        composable(tabBarItemsList[3].title) {
                            MoreView()
                        }
                    }
                }
            }
        }
    }

    fun getFirebaseEvent() {
        RetrofitClient.apiService.getUsers().enqueue(object : Callback<List<EventObject>> {
            override fun onResponse(
                call: Call<List<EventObject>>, response: Response<List<EventObject>>
            ) {
                if (response.isSuccessful) {
                    eventsList = response.body()!! // This is a List<EventObject> parsed by Gson
                    println("Event: $eventsList")
                } else {
                    println("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<EventObject>>, t: Throwable) {
                println("Failed to fetch data: ${t.message}")
            }
        })
    }
}

@Composable
fun MoreView() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Thing 1")
        Text("Thing 2")
        Text("Thing 3")
        Text("Thing 4")
        Text("Thing 5")
    }
}