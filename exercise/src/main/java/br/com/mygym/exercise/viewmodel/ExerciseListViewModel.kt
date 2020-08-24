package br.com.mygym.exercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.mygym.core.base.BaseViewModel
import br.com.mygym.exercise.model.ExerciseItem
import br.com.mygym.sdk.repository.ExerciseRepository
import com.sympla.organizer.core.livedata.SingleLiveEvent
import br.com.mygym.core.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseListViewModel(private val repository: ExerciseRepository): BaseViewModel() {
    private val _exerciseListLiveData = SingleLiveEvent<Result<List<ExerciseItem>>>()
    val exerciseListLiveData: LiveData<Result<List<ExerciseItem>>>
        get() = _exerciseListLiveData

    fun loadExercises() {
        viewModelScope.launch(Dispatchers.IO) {
            _exerciseListLiveData.postValue(Result.Loading)
            _exerciseListLiveData.postValue(
                repository.findAll().transform { data ->
                    data.map { ExerciseItem(it) }
                }
            )

        }
    }

    fun loadExercisesByFilter(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _exerciseListLiveData.postValue(Result.Loading)
            _exerciseListLiveData.postValue(
                repository.findAll().transform { data ->
                    data.map { ExerciseItem(it) }
                }
            )
        }
    }

}