package com.example.moviesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase:RoomDatabase() {
    abstract fun getMovieDao():MovieDao

    companion object{ //for singleton pattern
        @Volatile
        private var instance: MovieDatabase? =null //instance holds the singleton instance of node db
        private val Lock= Any() //synchronize the database creation process


        operator fun invoke( context: Context)= instance?: //custom invoke operator for companion object
        synchronized(Lock){ //to maintain thread safety
            instance?:
            createDatabase(context).also{
                instance= it
            }
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                name = "movie_db"
            ).build()


    }
}