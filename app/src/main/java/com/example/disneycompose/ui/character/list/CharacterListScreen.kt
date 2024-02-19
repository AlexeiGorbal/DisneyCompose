package com.example.disneycompose.ui.character.list

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.DefaultStrokeLineWidth
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
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

    val state = viewModel.state.observeAsState().value

    when (state) {
        is CharacterListState.Loaded -> {
            LazyColumn {
                items(state.characters) {
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