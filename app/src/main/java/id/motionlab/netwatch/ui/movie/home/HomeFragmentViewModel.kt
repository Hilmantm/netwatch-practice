package id.motionlab.netwatch.ui.movie.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.motionlab.netwatch.data.sources.Resource
import id.motionlab.netwatch.modules.movie.model.Movie
import id.motionlab.netwatch.modules.movie.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val movieInteractor: MovieUseCase
): ViewModel() {

    val popularMovies: MutableLiveData<Resource<List<Movie>>> = MutableLiveData()

    fun getPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = movieInteractor.getPopularMovies()
            popularMovies.postValue(result)
            Log.d("HomeFragmentViewModel", "getPopularMovies: $result")
        }
    }

}