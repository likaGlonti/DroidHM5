package composable.superheroes.droidhm5

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import composable.superheroes.droidhm5.database.HealthTracker
import composable.superheroes.droidhm5.databinding.ActivityMainBinding
import composable.superheroes.droidhm5.vm.HealthTrackerViewModel
import composable.superheroes.droidhm5.vm.HealthTrackerViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<HealthTrackerViewModel>
    {
        HealthTrackerViewModelFactory(App.instance.db.healthTrackerDao())
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            calculate.setOnClickListener {
                viewModel.calculateHealthStatistics(
                    HealthTracker(
                        runningDistance = passIfDigit(swim),
                        swimmingDistance = passIfDigit(swim),
                        caloriesTaken = passIfDigit(calories)
                    )
                )
            }
            lifecycleScope.launch {
                viewModel.sumStateFlow.collect { healthStatistics ->

                    meanRunning.text = resources.getString(
                        R.string.ran_distance_mean,
                        "${healthStatistics?.meanOfRunDistance ?: 0}"
                    )
                    meanSwimming.text = resources.getString(
                        R.string.swam_distance_mean,
                        "${healthStatistics?.meanOfSwimDistance ?: 0}"
                    )
                    meanCalories.text = resources.getString(
                        R.string.calories_taken_mean,
                        "${healthStatistics?.meanOfCaloriesTaken ?: 0}"
                    )
                    sum.text = "${healthStatistics?.sumOfRunDistance ?: 0}"
                }
            }
        }
    }

    private fun passIfDigit(view: EditText) =
        if (view.text.isNotEmpty())
            view.text.toString().toFloat()
        else null
}
