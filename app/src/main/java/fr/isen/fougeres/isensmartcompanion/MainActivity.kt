package fr.isen.fougeres.isensmartcompanion

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.fougeres.isensmartcompanion.components.TabView
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList


val backgroundColor = Color(0xFF282828)

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { TabView(tabBarItemsList, navController) },
                containerColor = backgroundColor,
            ) {
                NavHost(
                    navController = navController, startDestination = getStartDestination()
                ) {
                    for (index in tabBarItemsList.indices) {
                        composable(tabBarItemsList[index].title) {
                            OnTabSelected(index, context)
                        }
                    }
                }
            }
        }
    }

    private fun getStartDestination(): String {
        return tabBarItemsList[0].title // Example start destination
    }

    @Composable
    private fun OnTabSelected(index: Int, context: Context) {
        when (index) {
            0 -> startActivityWithIntent(
                context,
                MainScreen::class.java,
                tabBarItemsList[0].title
            )

            1 -> startActivityWithIntent(
                context,
                EventScreen::class.java,
                tabBarItemsList[1].title
            )

            2 -> startActivityWithIntent(
                context,
                AgendaScreen::class.java,
                tabBarItemsList[2].title
            )

            3 -> startActivityWithIntent(
                context,
                HistoryScreen::class.java,
                tabBarItemsList[3].title
            )
        }
    }

    private fun startActivityWithIntent(
        context: Context,
        clazz: Class<*>,
        currentDestination: String
    ) {
        val intent = Intent(context, clazz)
        intent.putExtra("currentDestination", currentDestination)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        context.startActivity(intent)
    }
}

abstract class BaseScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { TabView(tabBarItemsList, navController) },
                containerColor = backgroundColor,
            ) {
                NavHost(
                    navController = navController, startDestination = getStartDestination()
                ) {
                    for (index in tabBarItemsList.indices) {
                        composable(tabBarItemsList[index].title) {
                            OnTabSelected(index, context)
                        }
                    }
                }
            }
        }
    }

    abstract fun getStartDestination(): String

    @Composable
    private fun OnTabSelected(index: Int, context: Context) {
        when (index) {
            0 -> OnTab0Selected(context)
            1 -> OnTab1Selected(context)
            2 -> OnTab2Selected(context)
            3 -> OnTab3Selected(context)
        }
    }

    @Composable
    open fun OnTab0Selected(context: Context) {
        // Default behavior for Tab 0
        startActivityWithIntent(context, MainScreen::class.java, tabBarItemsList[0].title)
    }

    @Composable
    open fun OnTab1Selected(context: Context) {
        // Default behavior for Tab 1
        startActivityWithIntent(context, EventScreen::class.java, tabBarItemsList[1].title)
    }

    @Composable
    open fun OnTab2Selected(context: Context) {
        // Default behavior for Tab 2
        startActivityWithIntent(context, AgendaScreen::class.java, tabBarItemsList[2].title)
    }

    @Composable
    open fun OnTab3Selected(context: Context) {
        // Default behavior for Tab 3
        startActivityWithIntent(context, HistoryScreen::class.java, tabBarItemsList[3].title)
    }

    private fun startActivityWithIntent(
        context: Context,
        clazz: Class<*>,
        currentDestination: String
    ) {
        val intent = Intent(context, clazz)
        intent.putExtra("currentDestination", currentDestination)
        context.startActivity(intent)
    }
}