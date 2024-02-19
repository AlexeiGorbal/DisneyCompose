package com.example.disneycompose.ui.character.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneycompose.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val state = MutableLiveData<CharacterListState>()

    init {
        state.value = CharacterListState.Loading
        viewModelScope.launch {
            try {
                state.value = CharacterListState.Loaded(repository.getCharacters())
            } catch (e: Exception) {
                state.value = CharacterListState.Error
            }
        }
    }
}