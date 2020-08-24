package br.com.mygym.sdk.repository

import br.com.mygym.core.data.Result
import br.com.mygym.sdk.database.entity.Exercise

interface ExerciseRepository {
    suspend fun insert(exercise: Exercise): Result<Long>

    suspend fun findAll(): Result<List<Exercise>>

    suspend fun findByFilter(text: String): Result<List<Exercise>>

    suspend fun delete(): Result<Int>

    suspend fun deleteById(exercise: Exercise): Result<Int>
}