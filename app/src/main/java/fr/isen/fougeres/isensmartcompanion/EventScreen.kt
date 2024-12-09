@file:Suppress("UNREACHABLE_CODE")

package fr.isen.fougeres.isensmartcompanion

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.isen.fougeres.isensmartcompanion.components.ProcessRetrievedEventList
import fr.isen.fougeres.isensmartcompanion.components.eventsList
import fr.isen.fougeres.isensmartcompanion.components.tabBarItemsList

class EventScreen : BaseScreen() {
    override fun getStartDestination(): String {
        return tabBarItemsList[1].title
    }

    @Composable
    override fun OnTab0Selected(context: Context) {
        super.OnTab0Selected(context)
    }

    @Composable
    override fun OnTab1Selected(context: Context) {
        Log.d("ZIMBABWE", "ASKIP C'EST CENSÉ MARCHER")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 25.dp)
                .heightIn(max = 750.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(100.dp)
            ) {
                item {
                    ProcessRetrievedEventList(eventsList)
                }
            }
        }
    }

    @Composable
    override fun OnTab2Selected(context: Context) {
        super.OnTab2Selected(context)
    }

    @Composable
    override fun OnTab3Selected(context: Context) {
        super.OnTab3Selected(context)
    }
}