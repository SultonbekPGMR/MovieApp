package com.codialstudent.movieapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codialstudent.movieapp.adapters.RvAdapter
import com.codialstudent.movieapp.databinding.ActivityEditBinding
import com.codialstudent.movieapp.models.MovieData
import com.codialstudent.movieapp.utils.MySharedPreference

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movie = intent.getSerializableExtra("keyMovie") as MovieData
        val position = intent.getIntExtra("keyPosition", 0)
        supportActionBar!!.title = movie.name

        binding.edtName.setText(movie.name)
        binding.edtActors.setText(movie.actors)
        binding.edtAbout.setText(movie.about)
        binding.edtDate.setText(movie.date)

        binding.btnSave.setOnClickListener {
            MySharedPreference.init(this)
            val movieList = MySharedPreference.moviesList
            movieList[position] = MovieData(
                binding.edtName.text.toString().trim(),
                binding.edtActors.text.toString().trim(),
                binding.edtAbout.text.toString().trim(),
                binding.edtDate.text.toString().trim()
            )
            MySharedPreference.moviesList = movieList
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            val main = MainActivity()
            main.adapter = RvAdapter(main,MySharedPreference.moviesList,main)
            main.adapter.notifyItemChanged(position)
            finish()

        }


    }
}