package id.motionlab.netwatch.modules.movie.repository

import id.motionlab.netwatch.data.sources.Resource
import id.motionlab.netwatch.modules.movie.model.Movie

interface MovieRepository {

    suspend fun getPopularMovies(): Resource<List<Movie>>

}