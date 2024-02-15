package id.motionlab.netwatch.modules.movie.interactor

import id.motionlab.netwatch.data.sources.Resource
import id.motionlab.netwatch.modules.movie.model.Movie
import id.motionlab.netwatch.modules.movie.repository.MovieRepository
import id.motionlab.netwatch.modules.movie.usecase.MovieUseCase
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieRepository: MovieRepository
): MovieUseCase {
    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return movieRepository.getPopularMovies()
    }
}