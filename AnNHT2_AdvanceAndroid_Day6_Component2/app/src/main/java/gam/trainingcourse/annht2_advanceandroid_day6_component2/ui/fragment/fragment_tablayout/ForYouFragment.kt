package gam.trainingcourse.annht2_advanceandroid_day6_component2.ui.fragment.fragment_tablayout


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import gam.trainingcourse.annht2_advanceandroid_day6_component2.adapter.GenreAdapter
import gam.trainingcourse.annht2_advanceandroid_day6_component2.adapter.MovieAdapter
import gam.trainingcourse.annht2_advanceandroid_day6_component2.databinding.FragmentForYouBinding
import gam.trainingcourse.annht2_advanceandroid_day6_component2.view_model.ForYouViewModel

class ForYouFragment : Fragment() {

    private var _binding:FragmentForYouBinding? = null
    private val binding get() = _binding!!

    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var topRatingMoviesAdapter: MovieAdapter
    private lateinit var genreAdapter: GenreAdapter

    private val viewModel: ForYouViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForYouBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularMoviesAdapter = MovieAdapter(requireActivity(), viewModel.popularMovies)
        topRatingMoviesAdapter = MovieAdapter(requireActivity(), viewModel.topRatingMovies)
        binding.rvNewReleaseFilms.layoutManager = createLayoutManager()
        binding.rvBollywoodFilms.layoutManager = createLayoutManager()

        binding.rvNewReleaseFilms.adapter = popularMoviesAdapter
        binding.rvBollywoodFilms.adapter =topRatingMoviesAdapter
        viewModel.initPopularMovie(requireContext(), popularMoviesAdapter)
        viewModel.initTopRatedMovie(requireContext(), topRatingMoviesAdapter)

        viewModel.initGenres(requireContext())
        genreAdapter = GenreAdapter(viewModel.genres)
        binding.rvTopGenresFilms.adapter = genreAdapter
        binding.rvTopGenresFilms.layoutManager = createLayoutManager()
    }

    private fun createLayoutManager() : LinearLayoutManager{
        val llm1 = LinearLayoutManager(requireContext())
        llm1.orientation = LinearLayoutManager.HORIZONTAL
        return llm1
    }

}