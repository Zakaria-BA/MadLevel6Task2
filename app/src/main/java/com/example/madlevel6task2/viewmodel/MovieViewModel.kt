package com.example.madlevel6task2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.madlevel6task2.repository.MovieRepository
import kotlinx.coroutines.launch
import kotlin.math.log

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepository = MovieRepository()

    val movies = movieRepository.movies

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val errorText: LiveData<String>
        get() = _errorText

    fun getMovies(){
        viewModelScope.launch {
            try {
                movieRepository.getAllMovies()
            } catch (error: MovieRepository.MovieDatabaseerror){
                _errorText.value = error.message
                Log.e("Movies error", error.cause.toString())
            }
        }
    }
}