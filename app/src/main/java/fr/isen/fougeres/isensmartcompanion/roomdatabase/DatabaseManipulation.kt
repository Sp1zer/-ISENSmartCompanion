package fr.isen.fougeres.isensmartcompanion.roomdatabase

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun addInteractionToDatabase(request: String, answer: String, context: Context) {
    CoroutineScope(Dispatchers.Main).launch {
        DatabaseManager.getInstance(context).executeDatabaseOperation { db ->
            val dao = db.userDao()
            dao.insertAll(User(dao.getUserCount() + 1, request, answer))
            Log.d("TEST", answer)
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

fun cleanEntryInDatabase(id: Int, context: Context) {
    CoroutineScope(Dispatchers.Main).launch {
        DatabaseManager.getInstance(context).executeDatabaseOperation { db ->
            /*TODO()*/
        }
    }
}

suspend fun getUserCount(context: Context): Int {
    return DatabaseManager.getInstance(context).executeDatabaseOperation { db ->
        db.userDao().getUserCount()
    }
}

suspend fun getRequestForUser(userId: Int, context: Context): String {
    return DatabaseManager.getInstance(context).executeDatabaseOperation { db ->
        db.userDao().getRequestByUserId(userId)
    }
}

suspend fun getAnswerForUser(userId: Int, context: Context): String {
    return DatabaseManager.getInstance(context).executeDatabaseOperation { db ->
        db.userDao().getAnswerByUserId(userId)
    }
}