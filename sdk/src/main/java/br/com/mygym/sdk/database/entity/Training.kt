package br.com.mygym.sdk.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TRAINING_TABLE_NAME = "training_info"
@Entity(tableName = TRAINING_TABLE_NAME)
data class Training (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_training")
    val id: Long,

    @ColumnInfo(name = "st_group")
    val group: String,

    @ColumnInfo(name = "st_exercise_name")
    val exerciseName: String,

    @ColumnInfo(name = "nm_repetition")
    val numberRepetition: Int,

    @ColumnInfo(name = "st_weight")
    val weight: Int,

    @ColumnInfo(name = "tm_interval_seconds")
    val interval: Int,

    @ColumnInfo(name = "st_observation")
    val observation: String
)
