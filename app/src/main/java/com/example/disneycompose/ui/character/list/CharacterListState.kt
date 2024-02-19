package com.example.disneycompose.ui.character.list

import com.example.disneycompose.model.Character

sealed class CharacterListState {

    data object Loading : CharacterListState()
    data object Error : CharacterListState()
    data class Loaded(val characters: List<Character>) : CharacterListState()
}