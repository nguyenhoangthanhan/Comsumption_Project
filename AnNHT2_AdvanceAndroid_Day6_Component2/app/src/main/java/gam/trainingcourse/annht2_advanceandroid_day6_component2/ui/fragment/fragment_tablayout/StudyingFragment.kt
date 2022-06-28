package gam.trainingcourse.annht2_advanceandroid_day6_component2.ui.fragment.fragment_tablayout

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import gam.trainingcourse.annht2_advanceandroid_day6_component2.R

class StudyingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_studying, container, false)
    }
}