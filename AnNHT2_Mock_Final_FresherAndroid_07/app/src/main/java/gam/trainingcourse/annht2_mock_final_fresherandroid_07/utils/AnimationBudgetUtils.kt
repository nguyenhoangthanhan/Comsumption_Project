package gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.R
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.view.fragment.ItemFragment

object AnimationBudgetUtils {

    private lateinit var animation: Animation

    fun startCountAnimation(priceStart: Int, priceEnd: Int, textView: TextView) {
        val animator = ValueAnimator.ofInt(priceStart, priceEnd)
        val animation1 = AlphaAnimation(0.2f, 1.0f)

        animator.duration = 500
        animator.addUpdateListener { animation ->
            textView.text = animation.animatedValue.toString()
        }

        animation1.duration = 500
        animation1.fillAfter = true
        textView.startAnimation(animation1)

        animator.start()
    }

    fun animationDisappear(context: Context, textView: TextView){
        animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        textView.startAnimation(animation)
    }

    fun animateExpenseFragment(expenseFragment: ItemFragment) {
        expenseFragment.animate()
    }

    fun unAnimateExpenseFragment(expenseFragment: ItemFragment) {
        expenseFragment.unAnimate()
    }

    fun animateFadeIn(view: View) {
        val fadeInAnimation = AlphaAnimation(0.5f, 1f)
        fadeInAnimation.interpolator = DecelerateInterpolator()
        fadeInAnimation.duration = 1500L
        view.startAnimation(fadeInAnimation)
    }

    fun animateExpenseText(
        viewContainTextView: View?
    ) {
        val newX = 5f
        val oldX = 0f

        val viewPaddingStartAnimator = ObjectAnimator.ofFloat(viewContainTextView, "translationX", oldX, newX)
        viewPaddingStartAnimator.apply {
            duration = 2000L
            start()
        }
    }

    private var selectedItemBudgetFragmentHeight: Int? = null
    private var selectedItemBudgetFragmentWidth: Int? = null

    fun saveSelectedItemBudgetFragmentSize(height: Int, width: Int) {
        selectedItemBudgetFragmentHeight = height
        selectedItemBudgetFragmentWidth = width
    }

    fun getSelectedItemBudgetFragmentHeight(): Int? {
        return selectedItemBudgetFragmentHeight
    }

    fun getSelectedItemBudgetFragmentWidth(): Int? {
        return selectedItemBudgetFragmentWidth
    }

    private var oldRulerHeight = 0

    fun increaseRulerCursorHeight(view: View) {
        if (oldRulerHeight == 0) oldRulerHeight = view.measuredHeight
        val increaseHeightAnimator =
            ValueAnimator.ofInt(
                oldRulerHeight,
                oldRulerHeight + Constant.INCREASE_RULER_CURSOR_VALUE,
            )

        increaseHeightAnimator.apply {
            duration = 500L
            addUpdateListener {
                val animatedValue = it.animatedValue as Int
                val layoutParams = view.layoutParams
                layoutParams.height = animatedValue
                view.layoutParams = layoutParams
            }
            start()
        }
    }

}
