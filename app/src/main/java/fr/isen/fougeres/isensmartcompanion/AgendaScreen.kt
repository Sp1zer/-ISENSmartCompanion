package fr.isen.fougeres.isensmartcompanion

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList

class AgendaScreen : BaseScreen() {
    override fun getStartDestination(): String {
        return tabBarItemsList[2].title
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
        Text(text = "Bisous", color = Color.White)
    }

    @Composable
    override fun OnTab3Selected(context: Context) {
        super.OnTab3Selected(context)
    }
}