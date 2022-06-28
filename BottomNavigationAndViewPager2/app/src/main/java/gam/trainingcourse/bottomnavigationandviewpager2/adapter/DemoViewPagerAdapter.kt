package gam.trainingcourse.bottomnavigationandviewpager2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import gam.trainingcourse.bottomnavigationandviewpager2.ui.fragment.Demo1Fragment
import gam.trainingcourse.bottomnavigationandviewpager2.ui.fragment.Demo2Fragment
import gam.trainingcourse.bottomnavigationandviewpager2.ui.fragment.Demo3Fragment
import gam.trainingcourse.bottomnavigationandviewpager2.utils.Constant

class DemoViewPagerAdapter(fm:FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return Constant.FRAGMENTS_NUMBER
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            Constant.FRAGMENT_Demo1Fragment -> Demo1Fragment()
            Constant.FRAGMENT_Demo2Fragment -> Demo2Fragment()
            Constant.FRAGMENT_Demo3Fragment -> Demo3Fragment()
            else -> Demo1Fragment()
        }
    }
}