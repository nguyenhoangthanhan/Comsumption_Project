package gam.trainingcourse.annht2_mock_final_fresherandroid_07.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.view.fragment.ItemFragment

class BudgetFragmentAdapter(
    val fragment: Fragment,
    private val itemBudgetFragments: List<ItemFragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return itemBudgetFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return itemBudgetFragments[position]
    }
}