package fr.isen.fougeres.isensmartcompanion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            )
            val alertsTab = TabBarItem(
                title = "Alerts",
                selectedIcon = Icons.Filled.Notifications,
                unselectedIcon = Icons.Outlined.Notifications,
            )
            val settingsTab = TabBarItem(
                title = "Settings",
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings
            )
            val moreTab = TabBarItem(
                title = "More",
                selectedIcon = Icons.Filled.List,
                unselectedIcon = Icons.Outlined.List
            )

            val tabBarItems = listOf(homeTab, alertsTab, settingsTab, moreTab)

            val navController = rememberNavController()
            ISENSmartCompanionTheme{
                Scaffold(
                    bottomBar = { TabView(tabBarItems, navController) },
                    containerColor = backgroundColor
                )
                {
                    NavHost(navController = navController, startDestination = homeTab.title) {
                        composable(homeTab.title) {
                            PreviewCenteredCroppedImage()
                            PreviewToastAndTextField()
                        }
                        composable(alertsTab.title) {
                            Text(alertsTab.title)
                        }
                        composable(settingsTab.title) {
                            Text(settingsTab.title)
                        }
                        composable(moreTab.title) {
                            //MoreView()
                        }
                    }
                }
            }
        }
    }
}