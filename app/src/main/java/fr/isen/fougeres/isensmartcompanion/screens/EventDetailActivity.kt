package fr.isen.fougeres.isensmartcompanion.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import fr.isen.fougeres.isensmartcompanion.backgroundColor
import fr.isen.fougeres.isensmartcompanion.components.EventDetails
import fr.isen.fougeres.isensmartcompanion.components.EventObject
import fr.isen.fougeres.isensmartcompanion.components.StylishButton
import fr.isen.fougeres.isensmartcompanion.notifications.EventUtils
import fr.isen.fougeres.isensmartcompanion.notifications.NotificationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class EventDetailActivity : ComponentActivity() {
    private lateinit var notificationPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var event: EventObject

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        NotificationUtils.createNotificationChannel(this) // Create the notification channel

        // Initialize the ActivityResultLauncher
        this.notificationPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    EventUtils.sendEventNotification(this, event)
                } else {
                    // Handle permission denial (e.g., show a message to the user)
                }
            }

        fun getEventData(intent: Intent, defaultEvent: EventObject): EventObject {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra("eventData", EventObject::class.java) ?: defaultEvent
            } else {
                intent.getSerializableExtra("eventData") as? EventObject ?: defaultEvent
            }
        }

        event = getEventData(
            intent, EventObject(
                id = "", // Default values
                title = "",
                description = "",
                date = "",
                location = "",
                category = ""
            )
        )

        setContent {
            EventDetails(event)
            StylishButton(
                24.toDouble(),
                4.0,
                backgroundColor,
                Color.Red,
                Color.White,
                Alignment.Center,
                text = "Get notified ?",
                12.0
            ) {
                val context = LocalContext.current
                // Launch a coroutine to handle the delay
                CoroutineScope(Dispatchers.Main).launch {
                    delay(10000) // 10 seconds delay

                    // Use the appropriate context for the EventUtils methods
                    EventUtils.checkAndRequestNotificationPermission(
                        context,
                        notificationPermissionLauncher
                    ) {
                        EventUtils.sendEventNotification(context, event)
                    }
                }
            }
        }
    }
}