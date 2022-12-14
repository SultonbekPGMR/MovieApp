package com.codialstudent.movieapp.ui

import android.content.Intent
import android.os.Bundle
import android.transition.Visibility
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.codialstudent.movieapp.adapters.RvAdapter
import com.codialstudent.movieapp.adapters.RvClick
import com.codialstudent.movieapp.databinding.ActivityMainBinding
import com.codialstudent.movieapp.databinding.RvItemBinding
import com.codialstudent.movieapp.models.MovieData
import com.codialstudent.movieapp.utils.MySharedPreference

open class MainActivity : AppCompatActivity(), RvClick {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: RvAdapter
    private lateinit var movieList: ArrayList<MovieData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPreference.init(this)
        movieList = MySharedPreference.moviesList
        if (movieList.isEmpty()){
            binding.tvNoData.visibility = View.VISIBLE
        }else{
            binding.tvNoData.visibility = View.INVISIBLE
        }
        adapter = RvAdapter(this, movieList, this)
        binding.rvMovie.adapter = adapter

        binding.btnActionAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }



    }

    override fun editMovie(movie: MovieData, position: Int) {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra("keyMovie", movie)
        intent.putExtra("keyPosition", position)
        startActivity(intent)
    }

    override fun deleteMovie(movie: MovieData, position: Int) {
        if (position==movieList.size){
            movieList.removeAt(position-1)
            MySharedPreference.moviesList = movieList
            adapter.notifyItemRemoved(position-1)
        }else{
            movieList.removeAt(position)
            MySharedPreference.moviesList = movieList
            adapter.notifyItemRemoved(position)
        }
        if (movieList.isEmpty()){
            binding.tvNoData.visibility = View.VISIBLE
        }else{
            binding.tvNoData.visibility = View.INVISIBLE
        }

    }

    override fun mainClick(movie: MovieData) {
        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()
        movieList.clear()
        movieList.addAll(MySharedPreference.moviesList)
        adapter.notifyDataSetChanged()
        if (movieList.isEmpty()){
            binding.tvNoData.visibility = View.VISIBLE
        }else{
            binding.tvNoData.visibility = View.INVISIBLE
        }

    }

}