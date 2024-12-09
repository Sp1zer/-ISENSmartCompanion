package fr.isen.fougeres.isensmartcompanion

import android.annotation.SuppressLint
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
            MainContent()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { TabView(tabBarItemsList, navController) },
        containerColor = backgroundColor,
    ) {
        NavHost(navController = navController, startDestination = tabBarItemsList[0].title) {
            tabBarItemsList.forEachIndexed { index, tabItem ->
                composable(tabItem.title) {
                    OnTabSelected(index)
                }
            }
        }
    }
}

@Composable
private fun OnTabSelected(index: Int) {
    val context = LocalContext.current
    val targetActivity = when (index) {
        0 -> MainScreen::class.java
        1 -> EventScreen::class.java
        2 -> AgendaScreen::class.java
        3 -> HistoryScreen::class.java
        else -> return // Handle unexpected index
    }
    startActivityWithIntent(context, targetActivity, tabBarItemsList[index].title)
}

private fun startActivityWithIntent(
    context: Context,
    clazz: Class<*>,
    currentDestination: String
) {
    val intent = Intent(context, clazz).apply {
        putExtra("currentDestination", currentDestination)
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }
    context.startActivity(intent)
}

abstract class BaseScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaseScreenContent()
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun BaseScreenContent() {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = { TabView(tabBarItemsList, navController) },
            containerColor = backgroundColor,
        ) {
            NavHost(navController = navController, startDestination = getStartDestination()) {
                tabBarItemsList.forEachIndexed { index, tabItem ->
                    composable(tabItem.title) {
                        OnTabSelected(index, LocalContext.current)
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
        val intent = Intent(context, clazz).apply {
            putExtra("currentDestination", currentDestination)
        }
        context.startActivity(intent)
    }
}