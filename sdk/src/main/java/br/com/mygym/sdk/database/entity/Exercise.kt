package br.com.mygym.sdk.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val EXERCISE_TABLE_NAME = "exercise_info"
@Entity(tableName = EXERCISE_TABLE_NAME)
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_exercise")
    val id: Long,

    @ColumnInfo(name = "st_name")
    val name: String,

    @ColumnInfo(name = "st_muscle")
    val muscle: String,
)
