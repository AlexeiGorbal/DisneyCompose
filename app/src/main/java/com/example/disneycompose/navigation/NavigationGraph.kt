package com.example.disneycompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.disneycompose.ui.character.information.InformationCharacterScreen
import com.example.disneycompose.ui.character.list.CharacterListScreen

const val LIST_SCREEN = "listScreen"
const val CHARACTER_SCREEN = "characterScreen"

@Composable
fun NavigationGraph(
    navigationController: NavHostController
) {
    NavHost(navController = navigationController, startDestination = LIST_SCREEN) {
        composable(LIST_SCREEN) {
            CharacterListScreen(navigationController)
        }

        composable("$CHARACTER_SCREEN/{id}", arguments = listOf(navArgument("id") {
            type = NavType.StringType
        })) {
            InformationCharacterScreen(it.arguments?.getString("id", "") ?: "")
            //          it.arguments?.getInt("id", 0) ?: 0)
        }
    }
}