package com.example.finalapp.models

data class CategoryModel(
    val name : String,
    val coverUrl : String,
    val movies : List<String>
) {
    constructor() : this("", "", listOf())
}
