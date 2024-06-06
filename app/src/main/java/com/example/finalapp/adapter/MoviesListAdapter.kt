package com.example.finalapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.finalapp.MoviePlayerActivity
import com.example.finalapp.models.MovieModel
import com.example.finalapp.databinding.MovieListItemRecyclerRowBinding
import com.google.firebase.firestore.FirebaseFirestore

class MoviesListAdapter (private val movieIdList : List<String>) :
    RecyclerView.Adapter<MoviesListAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: MovieListItemRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
        // bind data with view
        fun bindData(movieId: String, context: Context){

            FirebaseFirestore.getInstance().collection("movies")
                .document(movieId).get()
                .addOnSuccessListener {documentSnapshot->
                    val movie = documentSnapshot.toObject(MovieModel::class.java)
                    movie?.apply{
                        binding.movieTitleTextView.text = title
                        binding.movieSubtitleTextView.text = subtitle
                        Glide.with(binding.movieCoverImageView).load(coverUrl)
                            .apply(
                                RequestOptions().transform(RoundedCorners(33))
                            )
                            .into(binding.movieCoverImageView)
                        val context =  binding.root.context
                        binding.root.setOnClickListener{
                            val intent = Intent(context, MoviePlayerActivity::class.java)
                            intent.putExtra("movieId", movieId)
                            context.startActivity(intent)
                        }
                    }
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MovieListItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieIdList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(movieIdList[position],holder.itemView.context)
    }
}