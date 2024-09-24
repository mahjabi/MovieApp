package com.example.moviesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moviesapp.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insertMovie(movie: Movie)


      @Update
      suspend fun updateMovie(movie: Movie)

      @Delete
      suspend fun deleteMovie(movie: Movie)

      @Query("SELECT * FROM MOVIES ORDER BY id DESC ")
      fun getAllMovies(): LiveData<List<Movie>>

      @Query("SELECT *FROM MOVIES WHERE movtitle LIKE :query OR movdec LIKE :query")
      fun searchMovie(query: String):LiveData<List<Movie>>
}

