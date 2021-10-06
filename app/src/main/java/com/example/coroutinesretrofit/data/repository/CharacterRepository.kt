package com.fclarke.coroutinesretrofit.data.repository

import com.fclarke.coroutinesretrofit.data.entities.Character
import com.fclarke.coroutinesretrofit.data.local.CharacterDao
import com.fclarke.coroutinesretrofit.data.remote.CharacterRemoteDataSource
import com.fclarke.coroutinesretrofit.utils.performGetOperation
import javax.inject.Inject

class CharacterRepository @Inject constructor(//INJECT!!!
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDao
) {
    fun getCharacter(id:Long) = performGetOperation(//this is for a single character
        databaseQuery = { localDataSource.getCharacter(id) },//is it in the database?
        networkCall = { remoteDataSource.getCharacter(id) },//get network data in background anyway
        saveCallResult = { localDataSource.insert(it) } //save fresh data
    )

    fun getCharacters() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters(30) },
        saveCallResult = { localDataSource.insertAll(it.items as List<Character>) }
    )

}