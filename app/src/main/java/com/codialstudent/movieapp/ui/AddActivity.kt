package com.codialstudent.movieapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codialstudent.movieapp.databinding.ActivityAddBinding
import com.codialstudent.movieapp.models.MovieData
import com.codialstudent.movieapp.utils.MySharedPreference

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.isEnabled = true
        binding.btnSave.text = "Save"
        MySharedPreference.init(this)
        val movieList = MySharedPreference.moviesList


        binding.btnSave.setOnClickListener {

            val edtName = binding.edtName.text.toString().trim()
            val edtActors = binding.edtActors.text.toString().trim()
            val edtAbout = binding.edtAbout.text.toString().trim()
            val edtDate = binding.edtDate.text.toString().trim()

            if (edtName.isNotEmpty() && edtActors.isNotEmpty() && edtAbout.isNotEmpty() && edtDate.isNotEmpty()) {
                binding.btnSave.isEnabled = false
                binding.btnSave.text = "saving"
                movieList.add(MovieData(edtName, edtActors, edtAbout, edtDate))
                MySharedPreference.moviesList = movieList
                Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "Please fill out all forms", Toast.LENGTH_SHORT).show()
            }

        }

    }
}