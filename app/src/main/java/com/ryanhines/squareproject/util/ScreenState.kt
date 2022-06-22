package com.ryanhines.squareproject.util

sealed class ScreenState<T> (
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ScreenState<T>(data)
    class Loading<T>(data: T? = null) : ScreenState<T>(data)
    class Error<T>(message: String, data: T? = null) : ScreenState<T>(data, message)
    class Empty<T>(message: String) : ScreenState<T>(message = message)
}