package com.fclarke.coroutinesretrofit.data.remote

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.fclarke.coroutinesretrofit.MainApplication
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService
) : BaseDataSource() {
//. getResult encapsulates the Retrofit response in a Resource, so that we can catch errors nicely.
    val ai: ApplicationInfo =  MainApplication.applicationContext().packageManager
        .getApplicationInfo( MainApplication.applicationContext().packageName, PackageManager.GET_META_DATA)
    val value = ai.metaData["todoistKey"]
    var todoistAuth = "Bearer " + value.toString()

    suspend fun getCharacters(limit: Int) = getResult {
        characterService.getAllCharacters(
            todoistAuth,
            limit,
            "2021-08-01T20:16:09Z"
        )
    }
    suspend fun getCharacter(id: Long) = getResult {
        characterService.getCharacter(
            todoistAuth,
            id
        )
    }
}