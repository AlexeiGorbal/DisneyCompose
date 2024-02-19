package com.example.disneycompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Character(
    val id: String,
    val imageUrl: String?,
    val name: String,
    val films: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val videoGames: List<String>,
    val parkAttractions: List<String>
) : Parcelable