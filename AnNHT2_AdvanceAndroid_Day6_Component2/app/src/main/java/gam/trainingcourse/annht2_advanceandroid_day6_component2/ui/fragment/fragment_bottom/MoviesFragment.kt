package gam.trainingcourse.annht2_advanceandroid_day6_component2.ui.fragment.fragment_bottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import gam.trainingcourse.annht2_advanceandroid_day6_component2.R
import gam.trainingcourse.annht2_advanceandroid_day6_component2.databinding.FragmentMoviesBinding
import gam.trainingcourse.annht2_advanceandroid_day6_component2.viewpager_adapter.FmMoviesViewPagerAdapter

class MoviesFragment : Fragment() {

    private var _binding : FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fmMoviesViewPagerAdapter = FmMoviesViewPagerAdapter(requireActivity())

        binding.viewPager2FmMovies.adapter = fmMoviesViewPagerAdapter
        TabLayoutMediator(binding.tabLayoutFmMovies, binding.viewPager2FmMovies
        ) { tab, position -> when (position){
            0 -> {
                tab.text = getString(R.string.for_you)
            }
            1 -> {
                tab.text = getString(R.string.top_selling)
            }
            2 -> {
                tab.text = getString(R.string.new_releases)
            }
            3 -> {
                tab.text = getString(R.string.geners)
            }
            4 -> {
                tab.text = getString(R.string.studying)
            }
            5 -> {
                tab.text = getString(R.string.studying)
            }
            6 -> {
                tab.text = getString(R.string.studying)
            }
            7 -> {
                tab.text = getString(R.string.studying)
            }
            8 -> {
                tab.text = getString(R.string.studying)
            }
        }}.attach()
    }
}