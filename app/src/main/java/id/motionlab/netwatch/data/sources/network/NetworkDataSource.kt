package id.motionlab.netwatch.data.sources.network

import id.motionlab.netwatch.data.sources.network.response.BaseResponse
import id.motionlab.netwatch.data.sources.network.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkDataSource {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<BaseResponse<List<MovieResponse>>>

}