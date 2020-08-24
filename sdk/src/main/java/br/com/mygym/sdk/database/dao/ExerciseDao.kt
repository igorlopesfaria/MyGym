package br.com.mygym.sdk.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mygym.sdk.database.entity.EXERCISE_TABLE_NAME
import br.com.mygym.sdk.database.entity.Exercise

const val ORDER_BY_NAME_ASC = "st_name ASC"
@Dao
interface ExerciseDao {

    @Query("SELECT * FROM $EXERCISE_TABLE_NAME ORDER BY $ORDER_BY_NAME_ASC")
    fun findAll(): List<Exercise>

    @Query("SELECT * FROM $EXERCISE_TABLE_NAME WHERE st_name LIKE '%' || :text  || '%' AND st_muscle LIKE '%' || :text  || '%' ORDER BY $ORDER_BY_NAME_ASC")
    fun findByFilter(text: String): List<Exercise>

    @Query("DELETE FROM $EXERCISE_TABLE_NAME ")
    fun delete(): Int

    @Query("DELETE FROM $EXERCISE_TABLE_NAME WHERE id_exercise = :id")
    fun deleteById(id: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(exercise: Exercise): Long
}
