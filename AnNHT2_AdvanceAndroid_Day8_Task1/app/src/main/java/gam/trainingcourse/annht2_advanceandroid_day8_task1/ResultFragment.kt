package gam.trainingcourse.annht2_advanceandroid_day8_task1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import gam.trainingcourse.annht2_advanceandroid_day8_task1.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    val args: ResultFragmentArgs by navArgs()

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user: User = args.loginUserInfo
        Log.d("user_login", user.toString())
        binding.tvShowInFoLogin.text = user.toString()
    }
}