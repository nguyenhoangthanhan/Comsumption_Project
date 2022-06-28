package gam.trainingcourse.annht2_advanceandroid_day8_task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import gam.trainingcourse.annht2_advanceandroid_day8_task1.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    companion object{
        val TAG = LoginFragment::class.java.name
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnConfirm.setOnClickListener {
            val username:String = binding.edtUsername.text.toString()
            val password:String = binding.edtPassword.text.toString()
            if (username.isNotEmpty()){
                if (password.length >= 6){
                    val action = LoginFragmentDirections.actionLogin(User(username, password))
                    Navigation.findNavController(requireView()).navigate(action)
                }
                else{
                    Toast.makeText(requireContext()
                        , "Password must be more length 6 characters", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(requireContext(), "Please input username", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}