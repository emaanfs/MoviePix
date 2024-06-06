package com.example.finalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.finalapp.adapter.MoviesListAdapter
import com.example.finalapp.databinding.ActivityMovieListBinding
import com.example.finalapp.models.CategoryModel

class MovieListActivity : AppCompatActivity() {

    companion object{
        lateinit var category: CategoryModel
    }

    lateinit var binding: ActivityMovieListBinding
    lateinit var moviesListAdapter: MoviesListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameTextView.text = category.name  // extracting name of the genres on first page to this activity
        Glide.with(binding.categoryCoverImageView).load(category.categoryUrl) // for extracting image to this activity
            .apply(
                RequestOptions().transform(RoundedCorners(33))
            )
            .into(binding.categoryCoverImageView)

        setupMoviesListRecyclerView()
    }

    fun setupMoviesListRecyclerView(){
        moviesListAdapter = MoviesListAdapter(category.movies)
        binding.moviesListRecyclerView.layoutManager = LinearLayoutManager(this) // to display vertically only
        binding.moviesListRecyclerView.adapter = moviesListAdapter
    }

}