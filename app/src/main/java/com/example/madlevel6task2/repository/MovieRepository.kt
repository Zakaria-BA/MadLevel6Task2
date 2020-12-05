package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.Model.Movie
import com.example.madlevel6task2.api.MovieDatabaseApi
import com.example.madlevel6task2.api.MovieDatabaseApiService

class MovieRepository {
    private val movieApiService: MovieDatabaseApiService = MovieDatabaseApi.createApi()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val movies: LiveData<List<Movie>>
        get() = _movies

    suspend fun getAllMovies(){
        try {
            val movies = movieApiService.getMovies()

            _movies.value = movies
        } catch (error: Throwable){
            throw MovieDatabaseerror("Unable to get movies", error)
        }
    }

    class MovieDatabaseerror(message: String, cause: Throwable) : Throwable(message, cause)

}