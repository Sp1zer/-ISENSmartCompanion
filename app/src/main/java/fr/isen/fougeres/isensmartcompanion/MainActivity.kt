package fr.isen.fougeres.isensmartcompanion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.fougeres.isensmartcompanion.api.getFirebaseEvent
import fr.isen.fougeres.isensmartcompanion.components.TabView
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList
import fr.isen.fougeres.isensmartcompanion.screens.AgendaScreen
import fr.isen.fougeres.isensmartcompanion.screens.EventScreen
import fr.isen.fougeres.isensmartcompanion.screens.HistoryScreen
import fr.isen.fougeres.isensmartcompanion.screens.MainScreen


val backgroundColor = Color(0xFF282828)

class SharedViewModel : ViewModel() {
    var currentDestination: String? = null
}

class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFirebaseEvent()
        enableEdgeToEdge()

        // Set the current destination
        sharedViewModel.currentDestination = tabBarItemsList[0].title

        setContent {
            MainContent(sharedViewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent(sharedViewModel: SharedViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { TabView(tabBarItemsList, navController) }, // Ensure this is included
        containerColor = backgroundColor,
    ) {
        NavHost(navController = navController, startDestination = sharedViewModel.currentDestination ?: tabBarItemsList[0].title) {
            tabBarItemsList.forEachIndexed { index, tabItem ->
                composable(tabItem.title) {
                    when (index) {
                        0 -> MainScreen().MainScreenContent()
                        1 -> EventScreen().EventScreenContent()
                        2 -> AgendaScreen().AgendaScreenContent()
                        3 -> HistoryScreen().HistoryScreenContent()
                    }
                }
            }
        }
    }
}