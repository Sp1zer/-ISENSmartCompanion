@file:OptIn(ExperimentalMaterial3Api::class)

package fr.isen.fougeres.isensmartcompanion

/*import fr.isen.fougeres.isensmartcompanion.components.EventObject
import fr.isen.fougeres.isensmartcompanion.components.PreviewCenteredCroppedImage
import fr.isen.fougeres.isensmartcompanion.components.PreviewToastAndTextField
import fr.isen.fougeres.isensmartcompanion.components.ShowEventObject
import fr.isen.fougeres.isensmartcompanion.components.TabView
import fr.isen.fougeres.isensmartcompanion.components.TabBarItem
import fr.isen.fougeres.isensmartcompanion.components.eventObjectList*/


import fr.isen.fougeres.isensmartcompanion.components.*
//Je sais que vous avez dit pas de wildcard, mais c'est plus pratique et pour l'instant
// je n'ai eu aucun problème à cause de ça.

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.fougeres.isensmartcompanion.api.RetrofitClient

import fr.isen.fougeres.isensmartcompanion.ui.theme.ISENSmartCompanionTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

val backgroundColor = Color(0xFF282828)


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            PreviewToastAndTextField(75.0)
                        }
                        composable(tabBarItemsList[1].title) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize() // Fill the parent container (e.g., screen or parent Box)
                                    .wrapContentSize(Alignment.Center)
                            )
                            {
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(20.dp)
                                ) {
                                    items(count = eventObjectList.size) { index ->
                                        ShowEventObject(eventObjectList[index])
                                    }
                                }
                            }

                            //Text(eventsTab.title)
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

    fun getFirebaseEvent()
    {
        RetrofitClient.apiService.getUsers().enqueue(object : Callback<List<EventObject>> {
            override fun onResponse(
                call: Call<List<EventObject>>,
                response: Response<List<EventObject>>
            ) {
                if (response.isSuccessful) {
                    val users = response.body() // This is a List<User> parsed by Gson
                    users?.forEach {
                        //println("User: ${it.name}, Username: ${it.username}")
                    }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ISENSmartCompanionTheme {
        MoreView()
    }
}