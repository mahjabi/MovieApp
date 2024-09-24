package com.example.moviesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.moviesapp.model.Movie
import com.example.moviesapp.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(app:Application, private val movieRepository: MovieRepository): AndroidViewModel(app) {
    fun addMovie(movie: Movie)=
        viewModelScope.launch {
            movieRepository.insertMovie(movie)
        }
    fun deleteMovie(movie: Movie)=
        viewModelScope.launch {
            movieRepository.DeleteMovie(movie)
        }
    fun updateMovie(movie: Movie)=
        viewModelScope.launch {
            movieRepository.UpdateMovie(movie)
        }
    fun getAllMovies()= movieRepository.getAllMovies()
    fun searchMovie(query:String)= movieRepository.searchMovie(query)
        }
