package br.com.mygym.sdk.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.mygym.sdk.database.dao.CheckInDao
import br.com.mygym.sdk.database.dao.ExerciseDao
import br.com.mygym.sdk.database.entity.CheckIn
import br.com.mygym.sdk.database.dao.TrainingDao
import br.com.mygym.sdk.database.entity.Exercise
import br.com.mygym.sdk.database.entity.Training
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val DATABASE_NAME = "mygym-database"
@Database(
    entities = [CheckIn::class, Exercise::class, Training::class],
    version = 2,
    exportSchema = false
)
abstract class MyGymDatabase : RoomDatabase() {
    abstract fun checkInDao(): CheckInDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun trainingDao(): TrainingDao
}

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
}

fun provideDatabase(context: Context): MyGymDatabase =
    Room.databaseBuilder(
        context, MyGymDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()
