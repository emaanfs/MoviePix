package com.example.finalapp.models

data class MovieModel(
    val id : String,
    val title : String,
    val subtitle : String,
    val url : String,
    val coverUrl : String,
    val summary : String,
) {
    constructor() : this("", "", "", "", "","")
}
