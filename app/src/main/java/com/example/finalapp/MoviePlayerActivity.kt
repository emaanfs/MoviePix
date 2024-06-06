package com.example.finalapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.finalapp.adapter.CategoryAdapter
import com.example.finalapp.adapter.MoviesListAdapter
import com.example.finalapp.databinding.ActivityMovieListBinding
import com.example.finalapp.databinding.ActivityMoviePlayerBinding
import com.example.finalapp.models.CategoryModel
import com.example.finalapp.models.MovieModel
import com.google.firebase.firestore.FirebaseFirestore

class MoviePlayerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_player)

        val movieId = intent.getStringExtra("movieId")
        if (movieId != null) {
            FirebaseFirestore.getInstance().collection("movies")
                .document(movieId).get()
                .addOnSuccessListener { documentSnapshot ->
                    val movie = documentSnapshot.toObject(MovieModel::class.java)
                    movie?.apply {
                        findViewById<TextView>(R.id.movie_title_text_view).text = title
                        findViewById<TextView>(R.id.movie_subtitle_text_view).text = subtitle
                        findViewById<TextView>(R.id.movie_summary_text_view).text = summary
                        findViewById<TextView>(R.id.movie_genre_text_view).text = genre

                        Glide.with(this@MoviePlayerActivity).load(poster)
                            .into(findViewById<ImageView>(R.id.movie_poster_image_view)) //for movie poster

                        val playbtn = findViewById<ImageButton>(R.id.player_btn)
                        Glide.with(this@MoviePlayerActivity).load(url)
                            .into(findViewById<ImageButton>(R.id.player_btn))
                        playbtn.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW , Uri.parse(url)) // for clicking on the poster link
                            startActivity(intent)
                        }

                        Glide.with(this@MoviePlayerActivity).load(actor)
                            .apply(
                                RequestOptions().transform(RoundedCorners(33))
                            )
                            .into(findViewById<ImageView>(R.id.movie_actor_image_view))
                        Glide.with(this@MoviePlayerActivity).load(actor2)
                            .apply(
                                RequestOptions().transform(RoundedCorners(33))
                            )
                            .into(findViewById<ImageView>(R.id.movie_actor2_image_view))
                        Glide.with(this@MoviePlayerActivity).load(actor3)
                            .apply(
                                RequestOptions().transform(RoundedCorners(33))
                            )
                            .into(findViewById<ImageView>(R.id.movie_actor3_image_view))
                        Glide.with(this@MoviePlayerActivity).load(actor4)
                            .apply(
                                RequestOptions().transform(RoundedCorners(33))
                            )
                            .into(findViewById<ImageView>(R.id.movie_actor4_image_view))
                    }
                }
        }
    }

}

