package br.com.mygym.sdk.repository

import br.com.mygym.core.data.DataBoundResource
import br.com.mygym.core.data.Result
import br.com.mygym.sdk.database.dao.CheckInDao
import br.com.mygym.sdk.database.entity.CheckIn

class CheckInRepositoryImpl(private val checkInDao: CheckInDao) : CheckInRepository {

    override suspend fun insert(checkIn: CheckIn): Result<Long> =
        DataBoundResource<Long>().fetchData(
            load = {
                checkInDao.insert(checkIn)
            }
        )

    override suspend fun findAll(): Result<List<CheckIn>> =
        DataBoundResource<List<CheckIn>>().fetchData(
            load = {
                checkInDao.findAll()
            }
        )

    override suspend fun delete(): Result<Int> =
        DataBoundResource<Int>().fetchData(
            load = {
                checkInDao.delete()
            }
        )

    override suspend fun deleteById(checkIn: CheckIn): Result<Int> =
        DataBoundResource<Int>().fetchData(
            load = {
                checkInDao.deleteById(checkIn.id)
            }
        )
}