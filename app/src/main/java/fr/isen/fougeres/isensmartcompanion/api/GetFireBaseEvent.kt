package fr.isen.fougeres.isensmartcompanion.api

import fr.isen.fougeres.isensmartcompanion.components.EventObject
import fr.isen.fougeres.isensmartcompanion.eventsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getFirebaseEvent() {
    RetrofitClient.apiService.getUsers().enqueue(object : Callback<List<EventObject>> {
        override fun onResponse(
            call: Call<List<EventObject>>, response: Response<List<EventObject>>
        ) {
            if (response.isSuccessful) {
                eventsList = response.body()!! // This is a List<EventObject> parsed by Gson
                println("Event: $eventsList")
            } else {
                println("Error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<List<EventObject>>, t: Throwable) {
            println("Failed to fetch data: ${t.message}")
        }
    })
}