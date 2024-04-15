package com.example.disneycompose.ui.character.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneycompose.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CharacterListState>(CharacterListState.Loading)
    val state: Flow<CharacterListState>
        get() = _state

    init {
        _state.value = CharacterListState.Loading
        viewModelScope.launch {
            try {
                _state.value = CharacterListState.Loaded(repository.getCharacters())
            } catch (e: Exception) {
                _state.value = CharacterListState.Error
            }
        }
    }
}