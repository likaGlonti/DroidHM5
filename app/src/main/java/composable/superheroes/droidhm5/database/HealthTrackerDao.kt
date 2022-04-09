package composable.superheroes.droidhm5.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HealthTrackerDao {

    @Query("SELECT * FROM healthtracker")
    suspend fun getAll(): List<HealthTracker>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: HealthTracker)
}