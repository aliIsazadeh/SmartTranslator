package com.esum.common.constraints

sealed class ResultConstraints<T>(val data : T? = null , val message : String? = null) {

    class Loading<T>() : ResultConstraints<T>()
    class Success<T>(data: T) : ResultConstraints<T>(data = data)
    class Error<T>(message: String) : ResultConstraints<T>(message = message)
}








