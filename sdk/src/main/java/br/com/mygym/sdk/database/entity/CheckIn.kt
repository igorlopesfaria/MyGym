package br.com.mygym.sdk.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val CHECKIN_TABLE_NAME = "checkin_info"
@Entity(tableName = CHECKIN_TABLE_NAME)
data class CheckIn (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_checkin")
    val id: Long,

    @ColumnInfo(name = "dt_checkin")
    val dateCheckIn: Long,

    @ColumnInfo(name = "st_group")
    val group: String,
)
