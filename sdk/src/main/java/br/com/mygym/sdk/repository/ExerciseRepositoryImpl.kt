package br.com.mygym.sdk.repository

import br.com.mygym.core.data.DataBoundResource
import br.com.mygym.core.data.Result
import br.com.mygym.sdk.database.dao.ExerciseDao
import br.com.mygym.sdk.database.entity.Exercise

class ExerciseRepositoryImpl(private val exerciseDao: ExerciseDao) : ExerciseRepository {

    override suspend fun insert(exercise: Exercise): Result<Long> =
        DataBoundResource<Long>().fetchData(
            load = {
                exerciseDao.insert(exercise)
            }
        )

    override suspend fun findAll(): Result<List<Exercise>> =
        DataBoundResource<List<Exercise>>().fetchData(
            load = {
                exerciseDao.findAll()
            }
        )

    override suspend fun findByFilter(text: String): Result<List<Exercise>> =
        DataBoundResource<List<Exercise>>().fetchData(
            load = {
                exerciseDao.findByFilter(text)
            }
        )

    override suspend fun delete(): Result<Int> =
        DataBoundResource<Int>().fetchData(
            load = {
                exerciseDao.delete()
            }
        )

    override suspend fun deleteById(exercise: Exercise): Result<Int> =
        DataBoundResource<Int>().fetchData(
            load = {
                exerciseDao.deleteById(exercise.id)
            }
        )
}