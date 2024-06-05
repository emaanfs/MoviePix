package com.example.finalapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.finalapp.MoviePlayerActivity
import com.example.finalapp.models.MovieModel
import com.example.finalapp.databinding.SectionMovieListRecyclerRowBinding
import com.google.firebase.firestore.FirebaseFirestore

class SectionMovieListAdapter (private val movieIdList : List<String>) :
    RecyclerView.Adapter<SectionMovieListAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: SectionMovieListRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
        // bind data with view
        fun bindData(movieId: String){

            FirebaseFirestore.getInstance().collection("movies")
                .document(movieId).get()
                .addOnSuccessListener {
                    val movie = it.toObject(MovieModel::class.java)
                    movie?.apply{
                        binding.movieTitleTextView.text = title
                        binding.movieSubtitleTextView.text = subtitle
                        Glide.with(binding.movieCoverImageView).load(coverUrl)
                            .apply(
                                RequestOptions().transform(RoundedCorners(32))
                            )
                            .into(binding.movieCoverImageView)
                        binding.root.setOnClickListener {
                                it.context.startActivity(Intent(it.context,MoviePlayerActivity::class.java))
                        }
                    }
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SectionMovieListRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieIdList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(movieIdList[position])
    }
}