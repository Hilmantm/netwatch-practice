package id.motionlab.netwatch.ui.movie.home

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import id.motionlab.netwatch.R
import id.motionlab.netwatch.data.sources.Resource
import id.motionlab.netwatch.ui.movie.adapter.MovieAdapter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModels()

    // adapter
    private lateinit var popularMovieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get arguments here

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set default value
        val popularMovieView = view.findViewById<LinearLayout>(R.id.popular_movie)
        val popularMovieTitle = popularMovieView.findViewById<TextView>(R.id.movie_list_title)
        val popularMovieRv = popularMovieView.findViewById<RecyclerView>(R.id.movie_list)

        popularMovieTitle.text = "Popular Movie"
        popularMovieAdapter = MovieAdapter()

        viewModel.getPopularMovies()
        viewModel.popularMovies.observe(viewLifecycleOwner) { popularMovies ->
            when(popularMovies) {
                is Resource.Success -> {
                    popularMovieAdapter.setItems(popularMovies.data ?: listOf())
                    popularMovieRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    popularMovieRv.adapter = popularMovieAdapter
                }
                is Resource.Error -> {
                    Log.e("HomeFragment", "onViewCreated: ${popularMovies.message}")
                    Toast.makeText(context, popularMovies.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }

}