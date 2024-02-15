package id.motionlab.netwatch.modules.movie.usecase

import id.motionlab.netwatch.data.sources.Resource
import id.motionlab.netwatch.modules.movie.model.Movie

interface MovieUseCase {

    suspend fun getPopularMovies(): Resource<List<Movie>>

}