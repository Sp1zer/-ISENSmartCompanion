package fr.isen.fougeres.isensmartcompanion.roomdatabase

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun addInteractionToDatabase(request: String, answer: String, context: Context) {
    // Run the database operation within a coroutine
    CoroutineScope(Dispatchers.Main).launch {
        DatabaseManager.getInstance(context).executeDatabaseOperation { db ->
            val dao = db.userDao()
            dao.insertAll(User(dao.getUserCount() + 1, request, answer))
            Log.d("ZIMBABWE", request + dao.getUserCount().toString())
        }
    }
}

fun cleanDatabase(context: Context) {
    CoroutineScope(Dispatchers.Main).launch {
        DatabaseManager.getInstance(context).executeDatabaseOperation { db ->
            val dao = db.userDao()
            dao.deleteAllUsers()
        }
        Log.d("ZIMBABWE", "Database cleaned.")
    }
}

fun cleanEntryInDatabase(id: Int,context: Context)
{
    CoroutineScope(Dispatchers.Main).launch {
        DatabaseManager.getInstance(context).executeDatabaseOperation { db ->
            /*TODO()*/
        }
        Log.d("ZIMBABWE", "Database cleaned.")
    }
}