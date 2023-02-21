package com.cblanco.rickandmortychars.core.extensions

import com.cblanco.rickandmortychars.R
import com.cblanco.rickandmortychars.core.domain.RequestFailure


fun RequestFailure.errorManager() =
    when (this) {
        is RequestFailure.NoConnectionError ->  R.string.connection_error_message
        is RequestFailure.ApiError -> message
        else -> R.string.default_error_message
    }