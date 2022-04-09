package composable.superheroes.droidhm5.vm

import androidx.lifecycle.ViewModelProvider
import composable.superheroes.droidhm5.database.HealthTrackerDao

class HealthTrackerViewModelFactory(private val db: HealthTrackerDao) : ViewModelProvider.Factory {
    override fun <T : androidx.lifecycle.ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(HealthTrackerDao::class.java).newInstance(db)
    }
}