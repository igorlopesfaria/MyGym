package br.com.mygym.core.data

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val error: Error) : Result<Nothing>()
    object Loading : Result<Nothing>()

    fun <C> transform(transformation: (R) -> C): Result<C> {
        return when (this) {
            is Success -> Success(
                transformation(data)
            )
            is Failure -> Failure(
                error
            )
            is Loading -> Loading
        }
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Error[exception=$error]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null
