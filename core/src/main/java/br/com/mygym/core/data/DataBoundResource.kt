package br.com.mygym.core.data

class DataBoundResource<ResultType> {

    suspend fun fetchData(
        load: suspend () -> ResultType,
        saveResult: suspend (ResultType) -> Unit = {}
    ): Result<ResultType> {
        return try {
            val response = load()
            Result.Success(response)
        } catch (exception: Exception) {
            Result.Failure(Error.DatabaseException)
        }
    }
}
