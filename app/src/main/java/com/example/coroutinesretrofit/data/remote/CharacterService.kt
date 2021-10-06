package com.fclarke.coroutinesretrofit.data.remote

import com.fclarke.coroutinesretrofit.data.entities.Character
import com.fclarke.coroutinesretrofit.data.entities.CharacterList
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
//suspend: execute in a coroutine (similar to a thread) allowing us to keep the UI thread unblocked
// while long lasting operations such as getting our data from the internet are being executed.
interface CharacterService {
    @GET("completed/get_all")
    suspend fun getAllCharacters(
        @Header("Authorization") header: String,
        @Query("limit") limit: Int,
        @Query("since") since: String

    ): retrofit2.Response<CharacterList>

    @GET("items/get")
    suspend fun getCharacter(
        @Header("Authorization") header: String,
        @Query("item_id") id: Long

    ): retrofit2.Response<Character>

}