package com.example.disneycompose.ui.character.information

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.disneycompose.model.Character

@Composable
fun InformationCharacterScreen(
    id: String,
    viewModel: InformationCharacterViewModel = hiltViewModel()
) {

    viewModel.loadCharacter(id)
    val character = viewModel.character.observeAsState()

    Column {
        AsyncImage(model = character.value?.imageUrl, contentDescription = null)
        Text(text = character.value?.name ?: "")
        SuggestionChipExample(character = character.value!!)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionChipExample(character: Character) {

    character.films.forEach {
        if (it.isNotEmpty()) {
            Text(text = "films")
            SuggestionChip(
                onClick = { Log.d("Suggestion chip", "hello world") },
                label = { Text(it) }
            )
        }
    }

    character.shortFilms.forEach {
        if (it.isNotEmpty()) {
            Text(text = "shortFilms")
            SuggestionChip(
                onClick = { Log.d("Suggestion chip", "hello world") },
                label = { Text(it) }
            )
        }
    }

    character.tvShows.forEach {
        if (it.isNotEmpty()) {
            Text(text = "tvShows")
            SuggestionChip(
                onClick = { Log.d("Suggestion chip", "hello world") },
                label = { Text(it) }
            )
        }
    }

    character.videoGames.forEach {
        if (it.isNotEmpty()) {
            Text(text = "videoGames")
            SuggestionChip(
                onClick = { Log.d("Suggestion chip", "hello world") },
                label = { Text(it) }
            )
        }
    }

    character.parkAttractions.forEach {
        if (it.isNotEmpty()) {
            Text(text = "parkAttractions")
            SuggestionChip(
                onClick = { Log.d("Suggestion chip", "hello world") },
                label = { Text(it) }
            )
        }
    }
}