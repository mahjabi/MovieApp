package com.example.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.database.MovieDatabase
import com.example.moviesapp.repository.MovieRepository
import com.example.moviesapp.viewmodel.MovieViewModel
import com.example.moviesapp.viewmodel.MovieViewModelFactory

class  MainActivity : AppCompatActivity() {

    lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()

    }
    private fun setupViewModel(){
        val movieRepository= MovieRepository(  MovieDatabase(this))
        val viewModelProviderFactory=MovieViewModelFactory(application,movieRepository)
        movieViewModel =ViewModelProvider(this, viewModelProviderFactory)[MovieViewModel::class.java]
    }
}










