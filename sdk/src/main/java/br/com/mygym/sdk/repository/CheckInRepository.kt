package br.com.mygym.sdk.repository

import br.com.mygym.core.data.Result
import br.com.mygym.sdk.database.entity.CheckIn

interface CheckInRepository {
    suspend fun insert(checkIn: CheckIn): Result<Long>

    suspend fun findAll(): Result<List<CheckIn>>

    suspend fun delete(): Result<Int>

    suspend fun deleteById(checkIn: CheckIn): Result<Int>
}