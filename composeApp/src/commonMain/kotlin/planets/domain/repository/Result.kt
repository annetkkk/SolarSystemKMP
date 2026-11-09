package planets.domain.repository

sealed interface Result<out T> {

    data class Success<T>(
        val data: T
    ) : Result<T>

    data class Error(
        val exception: Throwable
    ) : Result<Nothing>

    val isSuccess: Boolean
        get() = this is Success

    val isError: Boolean
        get() = this is Error

    fun getOrNull(): T? = when (this) {
        is Success -> data
        is Error -> null
    }
}