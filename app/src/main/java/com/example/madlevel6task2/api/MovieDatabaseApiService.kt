package com.example.madlevel6task2.api

import com.example.madlevel6task2.Model.Movie
import retrofit2.http.GET


interface MovieDatabaseApiService {

    @GET()
    suspend fun getMovies(): List<Movie>
}