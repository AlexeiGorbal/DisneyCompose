package com.example.disneycompose.ui.character.list

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.disneycompose.R
import com.example.disneycompose.model.Character
import com.example.disneycompose.navigation.CHARACTER_SCREEN

const val LIST_IS_NOT_LOADED = "List is not loaded"

@Composable
fun CharacterListScreen(
    navigationController: NavHostController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState(CharacterListState.Loading)

    when (state.value) {
        is CharacterListState.Loaded -> {
            LazyColumn {
                items((state.value as CharacterListState.Loaded).characters) {
                    CharacterItem(it) { id ->
                        navigationController.navigate("$CHARACTER_SCREEN/$id")
                    }
                }
            }
        }

        is CharacterListState.Loading -> {
            IndeterminateLinearProgressIndicator()
        }

        is CharacterListState.Error -> {
            Toast.makeText(LocalContext.current, LIST_IS_NOT_LOADED, Toast.LENGTH_SHORT).show()
        }

        else -> {
            //do nothing
        }
    }
}

@Composable
fun CharacterItem(character: Character, onClick: (id: String) -> Unit) {
    Column(
        modifier = Modifier.selectable(
            selected = true,
            onClick = { onClick(character.id) }
        )
    ) {
        AsyncImage(model = character.imageUrl, contentDescription = null)
        Text(text = character.name)
    }
}

@Composable
fun IndeterminateLinearProgressIndicator() {

    LinearProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.default_padding))
    )
}