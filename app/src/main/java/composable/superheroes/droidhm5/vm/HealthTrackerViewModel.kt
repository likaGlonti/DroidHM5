package composable.superheroes.droidhm5.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import composable.superheroes.droidhm5.database.HealthTracker
import composable.superheroes.droidhm5.database.HealthTrackerDao
import composable.superheroes.droidhm5.model.HealthStatistics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HealthTrackerViewModel(private val db: HealthTrackerDao) : ViewModel() {

    val sumStateFlow = MutableStateFlow<HealthStatistics?>(null)

    fun calculateHealthStatistics(value: HealthTracker) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insertAll(value)

            val healthHistory = db.getAll()

            sumStateFlow.value = HealthStatistics(
                meanOfRunDistance = mean(healthHistory.map { it.runningDistance }),
                meanOfSwimDistance = mean(healthHistory.map { it.swimmingDistance }),
                meanOfCaloriesTaken = mean(healthHistory.map { it.caloriesTaken }),
                sumOfRunDistance = healthHistory.map { it.runningDistance }.filterNotNull().sum()
            )
        }
    }

    private fun mean(value: List<Float?>): Float {
        val list = value.filterNotNull()
        return list.sum() / list.size
    }
}