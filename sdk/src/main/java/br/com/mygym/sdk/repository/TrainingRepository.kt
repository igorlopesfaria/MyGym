package br.com.mygym.sdk.repository

import br.com.mygym.core.data.Result
import br.com.mygym.sdk.database.entity.Training

interface TrainingRepository {

    suspend fun insert(training: Training): Result<Long>

    suspend fun findById(training: Training): Result<Training>

    suspend fun findAll(): Result<List<Training>>

    suspend fun findByGroup(group: String): Result<List<Training>>

    suspend fun delete(): Result<Int>

    suspend fun deleteById(training: Training): Result<Int>

    suspend fun deleteByGroup(group: String): Result<Int>
}