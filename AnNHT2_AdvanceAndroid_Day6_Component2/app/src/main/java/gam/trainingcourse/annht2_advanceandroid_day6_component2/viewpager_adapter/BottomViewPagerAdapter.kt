package gam.trainingcourse.annht2_advanceandroid_day6_component2.viewpager_adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import gam.trainingcourse.annht2_advanceandroid_day6_component2.ui.fragment.fragment_bottom.*

class BottomViewPagerAdapter(fm:FragmentActivity)
    : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> HomeFragment()
            1 -> GamesFragment()
            2 -> MoviesFragment()
            3 -> BooksFragment()
            4 -> MusicFragment()
            else -> HomeFragment()
        }
    }
}