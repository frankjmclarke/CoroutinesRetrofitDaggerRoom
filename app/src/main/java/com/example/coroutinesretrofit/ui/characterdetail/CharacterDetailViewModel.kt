package com.fclarke.coroutinesretrofit.ui.characterdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.fclarke.coroutinesretrofit.data.entities.Character
import com.fclarke.coroutinesretrofit.data.repository.CharacterRepository
import com.fclarke.coroutinesretrofit.utils.Resource

class CharacterDetailViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _id = MutableLiveData<Long>()
    private val _content = MutableLiveData<String>()

    private val _character = _id.switchMap { id ->
        repository.getCharacter(id)
    }
    val character: LiveData<Resource<Character>> = _character

    fun start(id: Long) {
        _id.value = id
    }

}
