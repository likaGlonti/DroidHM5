package composable.superheroes.droidhm5.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HealthTracker(
    @PrimaryKey(autoGenerate = true) val ID: Int = 0,
    @ColumnInfo(name = "running_distance") val runningDistance: Float?,
    @ColumnInfo(name = "swimming_distance") val swimmingDistance: Float?,
    @ColumnInfo(name = "calories_taken") val caloriesTaken: Float?
)
