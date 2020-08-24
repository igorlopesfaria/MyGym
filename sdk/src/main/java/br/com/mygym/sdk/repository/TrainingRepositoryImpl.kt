package br.com.mygym.sdk.repository

import br.com.mygym.core.data.DataBoundResource
import br.com.mygym.core.data.Result
import br.com.mygym.sdk.database.dao.TrainingDao
import br.com.mygym.sdk.database.entity.Training

class TrainingRepositoryImpl (private val trainingDao: TrainingDao): TrainingRepository{

    override suspend fun insert(training: Training): Result<Long> =
        DataBoundResource<Long>().fetchData(
            load = {
                trainingDao.insert(training)
            }
        )

    override suspend fun findById(training: Training): Result<Training> =
        DataBoundResource<Training>().fetchData(
            load = {
                trainingDao.findById(training.id)
            }
        )


    override suspend fun findAll(): Result<List<Training>> =
        DataBoundResource<List<Training>>().fetchData(
            load = {
                trainingDao.findAll()
            }
        )

    override suspend fun findByGroup(group: String): Result<List<Training>>  =
        DataBoundResource<List<Training>>().fetchData(
            load = {
                trainingDao.findByGroup(group)
            }
        )

    override suspend fun delete(): Result<Int> =
        DataBoundResource<Int>().fetchData(
            load = {
                trainingDao.delete()
            }
        )


    override suspend fun deleteById(training: Training): Result<Int> =
        DataBoundResource<Int>().fetchData(
            load = {
                trainingDao.deleteById(training.id)
            }
        )

    override suspend fun deleteByGroup(group: String): Result<Int>  =
        DataBoundResource<Int>().fetchData(
            load = {
                trainingDao.deleteByGroup(group)
            }
        )
}