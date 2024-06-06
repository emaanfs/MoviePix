package com.example.finalapp.models

data class CategoryModel(
    val name : String,
    val coverUrl : String,
    val movies : List<String>,
    val categoryUrl : String,
) {
    constructor() : this("", "", listOf(),"")
}
