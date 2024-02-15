package id.motionlab.netwatch.data.sources.network.response

import com.google.gson.annotations.SerializedName
import id.motionlab.netwatch.modules.movie.model.Movie

data class MovieResponse(

    val id: Int,

    val title: String,

    val poster_path: String = "",

    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String

) {

    fun toModel(): Movie {
        return Movie(
            id = this.id,
            title = this.title,
            overview = this.overview,
            poster = "https://image.tmdb.org/t/p/w500/${this.poster_path}",
            releaseData = this.releaseDate
        )
    }

}
