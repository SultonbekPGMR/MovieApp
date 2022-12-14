package com.codialstudent.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codialstudent.movieapp.R
import com.codialstudent.movieapp.databinding.ActivityShowBinding
import com.codialstudent.movieapp.models.MovieData

class ShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movie = intent.getSerializableExtra("movie") as MovieData

        supportActionBar!!.title = movie.name
        binding.edtName.text = "Movie name: ${movie.name}"
        binding.edtActors.text = "Movie actors: ${movie.actors}"
        binding.edtAbout.text = "About movie: ${movie.about}"
        binding.edtDate.text = "Date: ${movie.date}"

        binding.btnClose.setOnClickListener {
            finish()
        }

    }
}