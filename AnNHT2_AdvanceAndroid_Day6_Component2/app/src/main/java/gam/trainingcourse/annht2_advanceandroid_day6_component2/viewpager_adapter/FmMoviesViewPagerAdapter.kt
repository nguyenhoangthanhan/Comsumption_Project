package gam.trainingcourse.annht2_advanceandroid_day6_component2.viewpager_adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import gam.trainingcourse.annht2_advanceandroid_day6_component2.ui.fragment.fragment_bottom.*
import gam.trainingcourse.annht2_advanceandroid_day6_component2.ui.fragment.fragment_tablayout.*

class FmMoviesViewPagerAdapter(fm:FragmentActivity)
    : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 8
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> ForYouFragment()
            1 -> TopSellingFragment()
            2 -> NewReleasesFragment()
            3 -> GenresFragment()
            4 -> StudyingFragment()
            5 -> StudyingFragment()
            6 -> StudyingFragment()
            7 -> StudyingFragment()
            else -> ForYouFragment()
        }
    }
}