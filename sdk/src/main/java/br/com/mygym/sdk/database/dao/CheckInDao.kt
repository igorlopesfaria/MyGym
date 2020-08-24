package br.com.mygym.sdk.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mygym.sdk.database.entity.CHECKIN_TABLE_NAME
import br.com.mygym.sdk.database.entity.CheckIn

const val ORDER_BY = "dt_checkin DESC"
@Dao
interface CheckInDao {

    @Query("SELECT * FROM $CHECKIN_TABLE_NAME ORDER BY $ORDER_BY")
    fun findAll(): List<CheckIn>

    @Query("DELETE FROM $CHECKIN_TABLE_NAME ")
    fun delete(): Int

    @Query("DELETE FROM $CHECKIN_TABLE_NAME WHERE id_checkin = :id")
    fun deleteById(id: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(checkin: CheckIn): Long
}
