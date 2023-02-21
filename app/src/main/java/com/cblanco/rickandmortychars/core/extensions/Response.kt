package com.cblanco.rickandmortychars.core.extensions

import android.util.Log
import com.cblanco.rickandmortychars.core.data.api.FailureFactory
import com.cblanco.rickandmortychars.core.domain.ResultOf
import retrofit2.Response

fun <T, R> Response<T>.safeCall(
    transform: (T) -> R,
    errorFactory: FailureFactory = FailureFactory()
): ResultOf<R> {
    val body = body()
    return when {
        isSuccessful && body != null -> ResultOf.Success(transform(body))
        else -> errorFactory.handleCode(code = code(), errorBody = errorBody())
    }
}