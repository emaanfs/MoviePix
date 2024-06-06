package com.example.finalapp.models

data class MovieModel(
    val id : String,
    val title : String,
    val subtitle : String,
    val url : String,
    val coverUrl : String,
    val summary : String,
    val genre : String,
    val poster : String,
    val actor : String,
    val actor2 : String,
    val actor3 : String,
    val actor4 : String,
) {
    constructor() : this("", "", "", "", "","","","","","","","")
}
