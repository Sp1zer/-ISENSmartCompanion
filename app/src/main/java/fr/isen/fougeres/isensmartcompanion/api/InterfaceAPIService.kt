package fr.isen.fougeres.isensmartcompanion.api

import fr.isen.fougeres.isensmartcompanion.components.EventObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://isen-smart-companion-default-rtdb.europe-west1.firebasedatabase.app/events.json")
    fun getUsers(): Call<List<EventObject>> // Replace `User` with your actual data class
}