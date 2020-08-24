package br.com.mygym.sdk.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mygym.sdk.database.entity.TRAINING_TABLE_NAME
import br.com.mygym.sdk.database.entity.Training

const val ORDER_BY_GROUP_ASC = "st_group ASC"
@Dao
interface TrainingDao {

    @Query("SELECT * FROM $TRAINING_TABLE_NAME WHERE id_training = :id")
    fun findById(id: Long): Training

    @Query("SELECT * FROM $TRAINING_TABLE_NAME WHERE st_group = :group")
    fun findByGroup(group: String): List<Training>

    @Query("SELECT * FROM $TRAINING_TABLE_NAME ORDER BY $ORDER_BY_GROUP_ASC")
    fun findAll(): List<Training>

    @Query("DELETE FROM $TRAINING_TABLE_NAME")
    fun delete(): Int

    @Query("DELETE FROM $TRAINING_TABLE_NAME WHERE id_training = :id")
    fun deleteById(id: Long): Int

    @Query("DELETE FROM $TRAINING_TABLE_NAME WHERE st_group = :group")
    fun deleteByGroup(group: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(training: Training): Long
}
