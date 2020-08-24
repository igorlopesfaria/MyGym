package br.com.mygym.exercise

import br.com.mygym.exercise.viewmodel.ExerciseListViewModel
import br.com.mygym.sdk.database.MyGymDatabase
import br.com.mygym.sdk.database.dao.ExerciseDao
import br.com.mygym.sdk.repository.ExerciseRepository
import br.com.mygym.sdk.repository.ExerciseRepositoryImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exerciseModule = module {
    factory { provideExerciseDao(get()) }
    single { provideExerciseRepository(get()) }
    viewModel {
        provideExerciseListViewModel(get())
    }
}
fun provideExerciseDao(database: MyGymDatabase): ExerciseDao {
    return database.exerciseDao()
}

fun provideExerciseListViewModel(repository: ExerciseRepository): ExerciseListViewModel {
    return ExerciseListViewModel(repository)
}

fun provideExerciseRepository(dao: ExerciseDao): ExerciseRepository {
    return ExerciseRepositoryImpl(dao)
}


