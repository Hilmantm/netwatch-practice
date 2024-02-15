package id.motionlab.netwatch.data

import android.util.Log
import id.motionlab.netwatch.data.sources.Resource
import id.motionlab.netwatch.data.sources.network.NetworkDataSource
import id.motionlab.netwatch.modules.movie.model.Movie
import id.motionlab.netwatch.modules.movie.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource, // if you have transaction from local database, add local data source interface here
): MovieRepository {
    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return withContext(Dispatchers.IO) {
            val call = networkDataSource.getPopularMovies("f76368dd8174f33f2ea3d5f032f831c0")
            if (!call.isSuccessful) {
                Log.e("MovieRepositoryImpl", "getPopularMovies: ${call.code()}")
                return@withContext Resource.Error("can't get popular movies")
            }
            val body = call.body()
            val movies = body?.results?.map { it.toModel() } ?: listOf()
            if (movies.isEmpty()) {
                Log.e("MovieRepositoryImpl", "movies empty")
            }
            Resource.Success(movies)
        }
    }
}