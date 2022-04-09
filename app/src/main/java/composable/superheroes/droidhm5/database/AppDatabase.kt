package composable.superheroes.droidhm5.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [HealthTracker::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun healthTrackerDao(): HealthTrackerDao
}