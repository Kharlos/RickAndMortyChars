package com.cblanco.rickandmortychars.core.data

import com.cblanco.rickandmortychars.features.characters.list.data.response.CharacterListApiResponse
import com.squareup.moshi.Moshi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

class MockWebServer {
    private val moshi = Moshi.Builder().build()

    private val basePath = "/src/test/resources/raw/"

    fun responseCharacterList(): Response<CharacterListApiResponse> {
        val response = moshi.adapter(CharacterListApiResponse::class.java).fromJson(getResponseJson("response_character_list.json"))!!
        return Response.success(response)
    }

    fun responseWithApiError(): Response<CharacterListApiResponse> {
        val errorResponseBody = getResponseJson("error_response_character_list.json").toResponseBody("application/json".toMediaTypeOrNull())
        return Response.error(400, errorResponseBody)
    }

    private fun getResponseJson(nameFile: String): String {
        return try {
            readFile(nameFile)
        } catch (e: Exception) {
            e.printStackTrace()
            "{}"
        }
    }

    @Throws(IOException::class)
    private fun readFile(path: String): String {
        var reader: BufferedReader? = null
        return try {
            val userDirPath = formatPath(System.getProperty("user.dir"))
            val fullPath = userDirPath + basePath + path
            reader = BufferedReader(InputStreamReader(FileInputStream(fullPath), "UTF-8"))
            reader.use {
                it.readText()
            }
        } catch (ignore: IOException) {
            ""
        } finally {
            reader?.close()
        }
    }

    private fun formatPath(path: String): String {
        val paths = path.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val listPaths = ArrayList(listOf(*paths))
        return if (listPaths.contains("app")) {
            path
        } else {
            "$path/app"
        }
    }

}