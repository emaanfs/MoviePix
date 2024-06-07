package com.example.finalapp


import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.adapter.CategoryAdapter
import com.example.finalapp.adapter.SectionMovieListAdapter
import com.example.finalapp.databinding.ActivityMainBinding
import com.example.finalapp.models.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCategories()
        setupSection(
            "section_1",
            binding.section1MainLayout,
            binding.section1Title,
            binding.section1RecyclerView
        )
        setupSection(
            "section_2",
            binding.section2MainLayout,
            binding.section2Title,
            binding.section2RecyclerView
        )

        val infobtn: ImageButton = binding.infobtn

        infobtn.setOnClickListener {
            showPopup()
        }
    }

    // categories
    fun getCategories(){ // GETTING DATA FROM THE WEBSITE
        FirebaseFirestore.getInstance().collection("category")
            .get().addOnSuccessListener {
                val categoryList = it.toObjects(CategoryModel::class.java)
                setupCategoryRecyclerView(categoryList)
            }
    }
    fun setupCategoryRecyclerView(categoryList: List<CategoryModel>){
        categoryAdapter = CategoryAdapter(categoryList)
        binding.categoriesRecyclerViews.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.categoriesRecyclerViews.adapter = categoryAdapter
    }
        //sections
    fun setupSection(id: String,mainLayout: RelativeLayout,titleView: TextView,recyclerView: RecyclerView) {
            FirebaseFirestore.getInstance().collection("sections")
                .document(id)
                .get().addOnSuccessListener {
                    val section = it.toObject(CategoryModel::class.java)
                    section?.apply {
                        mainLayout.visibility = View.VISIBLE
                        titleView.text = name
                        recyclerView.layoutManager = LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        recyclerView.adapter = SectionMovieListAdapter(movies)
                        mainLayout.setOnClickListener {
                            MovieListActivity.category = section
                            startActivity(Intent(this@MainActivity, MovieListActivity::class.java))
                        }
                    }
                }
    }
    // This func converts  pixels (dp) to pixels (px)
    private fun Int.dpToPx(): Int {
        val scale = resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }

    // FUNC TO SHOW INSTRUCTION POPUP
    private fun showPopup() {
        val instructpopup = Dialog(this)
        instructpopup.setContentView(R.layout.activity_information_pop_up)

        val window = instructpopup.window
        window?.setLayout(340.dpToPx(), 559.dpToPx()) // SET THE SIZE OF THE RESULT WINDOW
        instructpopup.show()

    }


}