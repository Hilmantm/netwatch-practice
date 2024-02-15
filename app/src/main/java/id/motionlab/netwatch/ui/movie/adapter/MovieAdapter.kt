package id.motionlab.netwatch.ui.movie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import id.motionlab.netwatch.R
import id.motionlab.netwatch.modules.movie.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var movies: List<Movie>

    fun setItems(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        private val banner: ShapeableImageView = view.findViewById(R.id.movie_poster)
        fun bindItem(movie: Movie) {
            banner.load(movie.poster)

            view.setOnClickListener {
                Toast.makeText(view.context, movie.title, Toast.LENGTH_SHORT).show()
            }
        }

    }

}