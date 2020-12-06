package com.example.madlevel6task2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.Model.Movie
import com.example.madlevel6task2.api.MovieDatabaseApi
import com.example.madlevel6task2.api.MovieDatabaseApiService
import kotlinx.coroutines.withTimeout

class MovieRepository {
    private val movieApiService: MovieDatabaseApiService = MovieDatabaseApi.createApi()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()

    val movies: LiveData<List<Movie>>
        get() = _movies

    suspend fun getAllMovies(year: Int){
        try {
            val result = withTimeout(5000) {
                movieApiService.getMovies(year)
            }

            _movies.value = result.results
        } catch (error: Throwable) {
            throw MovieDatabaseerror("Something went wrong", error)
        }
    }

    class MovieDatabaseerror(message: String, cause: Throwable) : Throwable(message, cause)

}