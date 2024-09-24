package com.example.moviesapp.repository

import androidx.lifecycle.LiveData
import com.example.moviesapp.database.MovieDatabase
import com.example.moviesapp.model.Movie

class MovieRepository(private val db: MovieDatabase) {
    suspend fun insertMovie(movie: Movie)=db.getMovieDao().insertMovie(movie)
    suspend fun UpdateMovie(movie: Movie)=db.getMovieDao().updateMovie(movie)
    suspend fun DeleteMovie(movie: Movie)=db.getMovieDao().deleteMovie(movie)

    fun getAllMovies()=db.getMovieDao().getAllMovies()
    fun searchMovie(query: String)=db.getMovieDao().searchMovie(query)
}