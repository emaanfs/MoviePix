package com.example.finalapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.finalapp.MovieListActivity
import com.example.finalapp.databinding.CategoryItemRecyclerRowBinding
import com.example.finalapp.models.CategoryModel

class CategoryAdapter (private val categoryList : List<CategoryModel> ) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding : CategoryItemRecyclerRowBinding):
        RecyclerView.ViewHolder(binding.root){
            //bind data with the view
            fun bindData(category : CategoryModel){
                //binding.nameTextView.text = category.name
                Glide.with(binding.coverImageView).load(category.coverUrl)
                    .apply(
                        RequestOptions().transform(RoundedCorners(33))
                    )
                    .into(binding.coverImageView)

                //START MOVIES LIST ACTIVITY
                val context = binding.root.context
                binding.root.setOnClickListener {
                    MovieListActivity.category = category
                    context.startActivity(Intent(context,MovieListActivity::class.java))
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =  CategoryItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindData(categoryList[position])
    }
}