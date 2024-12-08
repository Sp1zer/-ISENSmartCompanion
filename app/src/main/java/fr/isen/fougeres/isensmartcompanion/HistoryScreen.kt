package fr.isen.fougeres.isensmartcompanion

import android.content.Context
import androidx.compose.runtime.Composable
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList
import fr.isen.fougeres.isensmartcompanion.roomdatabase.ShowHistory

class HistoryScreen : BaseScreen() {
    override fun getStartDestination(): String {
        return tabBarItemsList[3].title
    }

    @Composable
    override fun OnTab0Selected(context: Context) {
        super.OnTab0Selected(context)
    }

    @Composable
    override fun OnTab1Selected(context: Context) {
        super.OnTab1Selected(context)
    }

    @Composable
    override fun OnTab2Selected(context: Context) {
        super.OnTab2Selected(context)
    }

    @Composable
    override fun OnTab3Selected(context: Context) {
        // Implement specific behavior for Tab 3
        ShowHistory() // Call a method to show history
    }
}