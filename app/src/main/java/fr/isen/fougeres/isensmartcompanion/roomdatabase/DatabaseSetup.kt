package fr.isen.fougeres.isensmartcompanion.roomdatabase

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "request") val request: String?,
    @ColumnInfo(name = "answer") val answer: String?
)

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE request LIKE :first AND " +
            "answer LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT COUNT(*) FROM user")
    fun getUserCount(): Int

    @Query("DELETE FROM user")
    fun deleteAllUsers()
}

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

class DatabaseManager private constructor(context: Context) {

    private val database: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "ISENSmartCompanionDatabase"
    ).build()

    // Singleton instance
    companion object {
        @Volatile
        private var INSTANCE: DatabaseManager? = null

        fun getInstance(context: Context): DatabaseManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DatabaseManager(context).also { INSTANCE = it }
            }
        }
    }

    fun getDatabase(): AppDatabase {
        return database
    }

    // Function to perform a query or other database operations off the main thread
    suspend fun <T> executeDatabaseOperation(block: suspend (AppDatabase) -> T): T {
        return withContext(Dispatchers.IO) { // Perform on IO dispatcher (background thread)
            block(database) // Execute the block of code using the database
        }
    }
}