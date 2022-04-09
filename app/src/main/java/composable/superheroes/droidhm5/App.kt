package composable.superheroes.droidhm5

import android.app.Application
import androidx.room.Room
import composable.superheroes.droidhm5.database.AppDatabase

class App : Application() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}