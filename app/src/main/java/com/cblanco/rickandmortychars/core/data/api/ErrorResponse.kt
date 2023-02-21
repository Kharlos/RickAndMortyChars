package com.cblanco.rickandmortychars.core.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
{
"code": 400,
"msg": "Error message"
}
 */
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @field:Json(name = "code")
    var code: Int,
    @field:Json(name = "msg")
    var message: String
)