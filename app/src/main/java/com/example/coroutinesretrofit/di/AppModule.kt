package com.fclarke.coroutinesretrofit.di

import android.content.Context
import com.fclarke.coroutinesretrofit.data.local.AppDatabase
import com.fclarke.coroutinesretrofit.data.local.CharacterDao
import com.fclarke.coroutinesretrofit.data.remote.CharacterRemoteDataSource
import com.fclarke.coroutinesretrofit.data.remote.CharacterService
import com.fclarke.coroutinesretrofit.data.repository.CharacterRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //annotates the object to indicate that we will get our dependencies from here.
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton//only one instance of the dependency will be created
    @Provides//the upcoming function will provide a dependency
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.todoist.com/sync/v8/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharacterService) = CharacterRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource,
                          localDataSource: CharacterDao) =
        CharacterRepository(remoteDataSource, localDataSource)
}