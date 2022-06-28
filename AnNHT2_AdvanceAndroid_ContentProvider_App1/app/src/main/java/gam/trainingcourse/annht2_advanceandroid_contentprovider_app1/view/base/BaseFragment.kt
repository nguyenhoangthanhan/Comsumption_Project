package gam.trainingcourse.annht2_advanceandroid_contentprovider_app1.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.IllegalStateException

abstract class BaseFragment<out T:ViewBinding>: Fragment()  {

    private var _binding: T? = null

    protected val binding
    get() = _binding?:throw IllegalStateException(
        "biding is only valid between onCreateView and onDestroyView"
    )

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerLiveData()
        initView()
        initData()
        initAction()
    }

    open fun observerLiveData(){}

    open fun initData(){}

    open fun initView(){}

    open fun initAction(){}

    @CallSuper
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}