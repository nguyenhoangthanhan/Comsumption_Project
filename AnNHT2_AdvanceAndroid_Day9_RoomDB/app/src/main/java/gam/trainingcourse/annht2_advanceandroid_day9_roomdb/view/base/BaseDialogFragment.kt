package gam.trainingcourse.annht2_advanceandroid_day9_roomdb.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import java.lang.IllegalStateException

abstract class BaseDialogFragment<VB: ViewBinding> : DialogFragment() {

    private var _binding: VB? = null
    protected val binding
    get() = _binding ?: throw IllegalStateException(
        "binding created between onCreateView and on DestroyView"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBiding(inflater, container, savedInstanceState)
        return binding.root
    }

    abstract fun createBiding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initComponent()
        initEvent()
    }

    open fun initView(){}

    open fun initComponent(){}

    open fun initEvent(){}

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}