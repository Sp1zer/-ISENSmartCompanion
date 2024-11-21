@file:OptIn(ExperimentalMaterial3Api::class)

package fr.isen.fougeres.isensmartcompanion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
/*import fr.isen.fougeres.isensmartcompanion.components.EventObject
import fr.isen.fougeres.isensmartcompanion.components.PreviewCenteredCroppedImage
import fr.isen.fougeres.isensmartcompanion.components.PreviewToastAndTextField
import fr.isen.fougeres.isensmartcompanion.components.ShowEventObject
import fr.isen.fougeres.isensmartcompanion.components.TabView*/
//Je sais que vous avez dit pas de wildcard, mais c'est plus pratique et pour l'instant
// je n'ai eu aucun problème à cause de ça.

import fr.isen.fougeres.isensmartcompanion.components.*
import fr.isen.fougeres.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

val backgroundColor = Color(0xFF282828)

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null
)


class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeTab = TabBarItem(
                title = "Accueil",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            )
            val eventsTab = TabBarItem(
                title = "Événements",
                selectedIcon = Icons.Filled.Notifications,
                unselectedIcon = Icons.Outlined.Notifications,
            )
            val settingsTab = TabBarItem(
                title = "Agenda",
                selectedIcon = Icons.Filled.DateRange,
                unselectedIcon = Icons.Outlined.DateRange
            )
            val historyTab = TabBarItem(
                title = "Historique",
                selectedIcon = Icons.Filled.List,
                unselectedIcon = Icons.Outlined.List
            )

            val tabBarItems = listOf(homeTab, eventsTab, settingsTab, historyTab)

            val navController = rememberNavController()
            ISENSmartCompanionTheme {
                Scaffold(
                    bottomBar = { TabView(tabBarItems, navController) },
                    containerColor = backgroundColor,
                ) {
                    NavHost(
                        navController = navController, startDestination = homeTab.title
                    ) {
                        composable(homeTab.title) {
                            PreviewCenteredCroppedImage()
                            PreviewToastAndTextField(75.0)
                        }
                        composable(eventsTab.title) {
                            LazyColumn {
                                item {
                                    ShowEventObject(EventObject(
                                        id = "BDE",
                                        title = "Soirée BDE",
                                        description = "Lorem Ipsum et tout le tralala.",
                                        date = "10/03/2025",
                                        location = "ISEN Yncréa Mediterranee Toulon",
                                        category = "Divers"
                                    ))
                                }

                                // Add 5 items
                                items(5) { index ->
                                    Text(text = "Item: $index")
                                }

                                // Add another single item
                                item {
                                    Text(text = "Last item")
                                }
                            }
                            Text(eventsTab.title)

                        }
                        composable(settingsTab.title) {
                            Text(settingsTab.title)
                        }
                        composable(historyTab.title) {
                            MoreView()
                        }
                    }
                }
            }
        }
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