package fr.isen.fougeres.isensmartcompanion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
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
import fr.isen.fougeres.isensmartcompanion.components.PreviewToastAndTextField
import fr.isen.fougeres.isensmartcompanion.components.TabView
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
                    bottomBar = { TabView(tabBarItems, navController, color = backgroundColor) },
                    containerColor = backgroundColor
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = homeTab.title
                    ) {
                        composable(homeTab.title) {
                            PreviewCenteredCroppedImage()
                            PreviewToastAndTextField(75.0)
                        }
                        composable(eventsTab.title) {
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