package com.example.moviesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val movtitle: String,
    val movdec: String
): Parcelable
