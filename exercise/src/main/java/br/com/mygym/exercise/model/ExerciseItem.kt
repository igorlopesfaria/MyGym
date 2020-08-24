package br.com.mygym.exercise.model

import android.os.Parcelable
import br.com.mygym.sdk.database.entity.Exercise
import kotlinx.android.parcel.Parcelize

@Parcelize
class ExerciseItem(
    val id: Long,
    val name: String,
    val muscle: String
) : Parcelable {
    constructor(entity: Exercise) : this(
            id = entity.id,
            name = entity.name,
            muscle = entity.muscle
    )
}