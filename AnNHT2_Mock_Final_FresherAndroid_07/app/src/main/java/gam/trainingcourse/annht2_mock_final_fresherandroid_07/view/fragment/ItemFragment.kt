package gam.trainingcourse.annht2_mock_final_fresherandroid_07.view.fragment

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.R
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity.Budget
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.AnimationBudgetUtils
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.ObjectWrapperForBinder
import android.animation.Animator

import android.animation.AnimatorListenerAdapter

import android.animation.ObjectAnimator

private const val ITEM_BUDGET_PARAM = "itemBudget"

class ItemFragment : Fragment() {
    private var cardViewItem: CardView? = null
    private var tvItemSpending: TextView? = null
    private var layoutTextView: LinearLayout? = null
    private var itemBudget: LinearLayout? = null
    private var tvItemName: TextView? = null
    private var imgItemBudget: ImageView? = null
    private var viewPaddingStart: View? = null
    private var viewPaddingEnd: View? = null

    var mDisplayBudget: Budget? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mDisplayBudget = (it.getBinder(ITEM_BUDGET_PARAM) as ObjectWrapperForBinder).data as Budget
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_budget, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        itemBudget = view.findViewById(R.id.itemBudget)
        layoutTextView = view.findViewById(R.id.layoutTextView)

        imgItemBudget = view.findViewById(R.id.imgItemBudget)
        tvItemName = view.findViewById(R.id.tvItemName)
        tvItemSpending = view.findViewById(R.id.tvItemSpending)
        cardViewItem = view.findViewById(R.id.cardViewItem)
        viewPaddingStart = view.findViewById(R.id.viewPaddingStart)
        viewPaddingEnd = view.findViewById(R.id.viewPaddingEnd)

        mDisplayBudget?.unselectedImage?.let { imgItemBudget?.setImageResource(it) }
        tvItemName?.text = mDisplayBudget?.budgetName
        tvItemSpending?.text = mDisplayBudget?.budgetConsuming.toString()
    }

    fun animate() {
        currentId = mDisplayBudget?.budgetId

        mDisplayBudget?.backgroundColor?.let {

            val mColorAnimation = ValueAnimator.ofObject(ArgbEvaluator()
                , cardViewItem?.cardBackgroundColor?.defaultColor, it)
            mColorAnimation.addUpdateListener {
                    animator -> cardViewItem?.setCardBackgroundColor(animator.animatedValue as Int)
            }
            mColorAnimation.duration = 100L
            mColorAnimation.start()
        }

        mDisplayBudget?.bgImg?.let {
            val alphaAnimator: ObjectAnimator =
                ObjectAnimator.ofFloat(imgItemBudget, View.ALPHA, 0.3f, 1.0f)
            alphaAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    imgItemBudget?.setBackgroundResource(it)
                }
            })
            alphaAnimator.duration = 500L
            alphaAnimator.start()
        }
        mDisplayBudget?.budgetId.let {
            it?.let { it1 -> setImageAnimate(it1) }
        }

        layoutTextView?.visibility = View.VISIBLE
        viewPaddingStart?.visibility = View.VISIBLE
        viewPaddingEnd?.visibility = View.VISIBLE

        //animationtv
        AnimationBudgetUtils.animateExpenseText(layoutTextView)
    }

    fun unAnimate() {
        cardViewItem?.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))

        mDisplayBudget?.budgetId.let {
            it?.let { it1 -> setImageUnAnimate(it1) }
        }
        mDisplayBudget?.bgImg?.let { imgItemBudget?.setBackgroundResource(R.color.white) }
        cardViewItem?.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))

        layoutTextView?.visibility = View.GONE
        viewPaddingStart?.visibility = View.GONE
        viewPaddingEnd?.visibility = View.GONE

    }

    private fun setImageAnimate(id: Long){
        when(id){
            1.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_restaurant)
            }
            2.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_grocery)
            }
            3.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_car_wash)
            }
            4.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_gym1)
            }
            5.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_messenger)
            }
            else ->{
                imgItemBudget?.setImageResource(R.drawable.ic_restaurant)
            }
        }
    }

    private fun setImageUnAnimate(id: Long){
        when(id){
            1.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_restaurant_unselected)
            }
            2.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_grocery_unselected)
            }
            3.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_car_wash_unselected)
            }
            4.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_gym1_unselected)
            }
            5.toLong() -> {
                imgItemBudget?.setImageResource(R.drawable.ic_messenger_unselected)
            }
            else ->{
                imgItemBudget?.setImageResource(R.drawable.ic_restaurant_unselected)
            }
        }
    }

    companion object {

        var currentId: Long? = 0

        @JvmStatic
        fun newInstance(mDisplayBudget: Budget) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putBinder(ITEM_BUDGET_PARAM, ObjectWrapperForBinder(mDisplayBudget))
                }
            }
    }
}