package fr.isen.fougeres.isensmartcompanion.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL =
        "https://isen-smart-companion-default-rtdb.europe-west1.firebasedatabase.app"  // Replace with your API base URL

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())  // Gson converter to parse the JSON
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}