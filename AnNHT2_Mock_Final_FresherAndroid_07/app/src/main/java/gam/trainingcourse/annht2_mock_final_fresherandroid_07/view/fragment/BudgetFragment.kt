package gam.trainingcourse.annht2_mock_final_fresherandroid_07.view.fragment

import android.animation.Animator
import android.animation.AnimatorInflater
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.R
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.adapter.*
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.databinding.FragmentBudgetBinding
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.model.entity.Budget
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.*
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.utils.Constant.TRANSITION_START_VALUE
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.view.base.BaseFragment
import gam.trainingcourse.annht2_mock_final_fresherandroid_07.viewmodel.BudgetViewModel
import kotlin.math.floor

@AndroidEntryPoint
class BudgetFragment : BaseFragment<FragmentBudgetBinding>() {

    private val budgetViewModel: BudgetViewModel by activityViewModels()

    private var alphaAnimator: Animator? = null
    private var scaleAnimation: Animation? = null
    private var budgetFragmentAdapter: BudgetFragmentAdapter? = null

    private var linearLayoutManager: LinearLayoutManager? = null

    private var listBudget = mutableListOf<Int>()
    private var averageBudget = 0
    private var currentPrice = 0

    private var listBudgetFragments = mutableListOf<ItemFragment>()

    private var currentItemId:Long = 1

    private var firstTimeScroll = false

    override fun createBiding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBudgetBinding = FragmentBudgetBinding.inflate(inflater, container, false)

    override fun initData() {
        budgetViewModel.initDataBudget(requireContext())

        budgetViewModel.getAllBudget()
    }

    override fun observerLiveData() {
        budgetViewModel.mBudget.observe(viewLifecycleOwner, { budgets ->

            if (budgets.isNotEmpty()){
                averageBudget = Constant.TOTAL_BUDGET / budgets.size
            }

            if (!budgets.isNullOrEmpty()){
                setupViewPager(budgets)
                updatePrice(budgets)
            }

            setupRecyclerviewRuler()
        })
    }

    override fun initView() {
        initAnimation()
    }

    private fun initAnimation() {
        alphaAnimator = AnimatorInflater.loadAnimator(activity, R.animator.animator_alpha)

        scaleAnimation = AnimationUtils.loadAnimation(activity, R.anim.scale_anim)
    }

    override fun initAction() {
        binding.btnSaveCurrentItem.setOnClickListener {
            saveNewConsuming(currentItemId)
        }
    }

    private fun setupViewPager(budgets: List<Budget>) {

        binding.listItemHorizontalViewPager2.let {

            listBudgetFragments.clear()
            val cafeFragment = ItemFragment.newInstance(budgets[0])
            listBudgetFragments.add(cafeFragment)
            val groceryFragment = ItemFragment.newInstance(budgets[1])
            listBudgetFragments.add(groceryFragment)
            val taxiFragment = ItemFragment.newInstance(budgets[2])
            listBudgetFragments.add(taxiFragment)
            val gymFragment = ItemFragment.newInstance(budgets[3])
            listBudgetFragments.add(gymFragment)
            val messengerFragment = ItemFragment.newInstance(budgets[4])
            listBudgetFragments.add(messengerFragment)

            listBudgetFragments.let { itemBudgetFragments ->
                budgetFragmentAdapter =
                    BudgetFragmentAdapter(this, itemBudgetFragments)
            }
            it.adapter = budgetFragmentAdapter


            val layoutManager = (it.getChildAt(0) as RecyclerView).layoutManager
            if (layoutManager != null) {
                layoutManager.isItemPrefetchEnabled = false
            }
            val recyclerView = it.getChildAt(0) as RecyclerView
            recyclerView.setItemViewCacheSize(2)

            it.clipToPadding = false //allow full width shown with padding
            it.clipChildren = false //to left and right pages is rendered
            it.offscreenPageLimit = 4 //number of pages is rendered
//            it.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compositePageTransformer = CompositePageTransformer() //combine multiple transformer
            compositePageTransformer.addTransformer(MarginPageTransformer(40)) // space between pages in pixel
            compositePageTransformer.addTransformer { page, position ->
                val positionRounded = floor(position).toInt()
                if (positionRounded != 0) {
                    val newLayoutParams = page.layoutParams
                    newLayoutParams?.height = AnimationBudgetUtils.getSelectedItemBudgetFragmentHeight()!! - 20
                    newLayoutParams?.width = AnimationBudgetUtils.getSelectedItemBudgetFragmentWidth()!! / 3
                    page.layoutParams = newLayoutParams
                } else {
                    val newLayoutParams = page.layoutParams
                    newLayoutParams?.height = AnimationBudgetUtils.getSelectedItemBudgetFragmentHeight()
                    newLayoutParams?.width = AnimationBudgetUtils.getSelectedItemBudgetFragmentWidth()
                    page.layoutParams = newLayoutParams

                    //the animation fade in for page
                    AnimationBudgetUtils.animateFadeIn(page)
                }
            }
            it.setPageTransformer(compositePageTransformer)
        }
    }

    private fun updatePrice(budgets: List<Budget>) {

        binding.listItemHorizontalViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            private var currentPosition = 0
            private var oldPositionOffset = 0

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                currentPosition = if (positionOffset < oldPositionOffset) {
                    if (positionOffset < TRANSITION_START_VALUE) {
                        position - 1
                    } else {
                        position
                    }
                } else {
                    if (positionOffset > TRANSITION_START_VALUE) {
                        position + 1
                    } else {
                        position
                    }
                }

                currentItemId = budgets[currentPosition].budgetId

                val priceStart: Int = binding.tvCurrentItemBudget.text.toString().toInt()
                val priceEnd: Int = budgets[currentPosition].budgetConsuming

                AnimationBudgetUtils.startCountAnimation(priceStart, priceEnd, binding.tvCurrentItemBudget)

                listBudgetFragments.let {
                    val selectedExpenseFragment = it[currentPosition]
                    val selectedExpenseMoney = selectedExpenseFragment.mDisplayBudget?.budgetConsuming ?: 0
                    //save width and height of fragment_item_budget fragment
                    if (!firstTimeScroll) {
                        selectedExpenseFragment.view?.let { view ->
                            AnimationBudgetUtils.saveSelectedItemBudgetFragmentSize(
                                view.measuredHeight, view.measuredWidth
                            )
                        }
                        firstTimeScroll = true
                    }

                    updateRuler((selectedExpenseMoney / 10) - 2)

                    //animation current expense fragment
                    AnimationBudgetUtils.animateExpenseFragment(selectedExpenseFragment)
                    //unAnimate other fragment
                    LogUtils.d("$currentPosition: $positionOffset")
                    for (itemBudgetFragment in it) {
                        if (itemBudgetFragment != selectedExpenseFragment) {
                            AnimationBudgetUtils.unAnimateExpenseFragment(itemBudgetFragment)
                        }
                    }
                }

                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                context?.let { AnimationBudgetUtils.animationDisappear(it, binding.tvTitleCurrentBudget) }
                context?.let { AnimationBudgetUtils.animationDisappear(it, binding.tvContentCurrentBudget) }
                AnimationBudgetUtils.increaseRulerCursorHeight(binding.cvRulerCursor)

            }
        })
    }

    private fun setupRecyclerviewRuler() {
        val rulerAdapter = RulerPickerAdapter()
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        for (n in 0..Constant.MAX_BUDGET step 10) {
            listBudget.add(n)
        }

        binding.rvGetItemValue.apply {
            layoutManager = linearLayoutManager
            adapter = rulerAdapter
        }
        rulerAdapter.differ.submitList(listBudget)

        setOnScrollListener()
    }

    private fun setOnScrollListener() {

        binding.rvGetItemValue.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                context?.let { AnimationBudgetUtils.animationDisappear(it, binding.tvTitleCurrentBudget) }
                context?.let { AnimationBudgetUtils.animationDisappear(it, binding.tvContentCurrentBudget) }
                AnimationBudgetUtils.increaseRulerCursorHeight(binding.cvRulerCursor)

                //the first seeing item in viewpager2
                val visiblePosition = linearLayoutManager!!.findFirstVisibleItemPosition()
                val firstPrice: Int = visiblePosition.plus(2) * 10

                Handler(Looper.getMainLooper()).postDelayed({
                    currentPrice = firstPrice
                }, 1)
                AnimationBudgetUtils.startCountAnimation(currentPrice, firstPrice, binding.tvCurrentItemBudget)
                notifyText(firstPrice)
            }
        })
    }

    private fun updateRuler(position: Int) {
        linearLayoutManager?.scrollToPositionWithOffset(position, 0)
        notifyText(position * 10)
    }

    private fun saveNewConsuming(id: Long){
        binding.btnSaveCurrentItem.setOnClickListener {
            val newPrice = binding.tvCurrentItemBudget.text.toString().toLong()
            budgetViewModel.updateSpendingBudgetById(newPrice, id)
        }

        firstTimeScroll = false
        currentItemId = 1
        AnimationBudgetUtils.animateExpenseFragment(listBudgetFragments[0])
        for (itemBudgetFragment in listBudgetFragments) {
            if (itemBudgetFragment != listBudgetFragments[0]) {
                AnimationBudgetUtils.unAnimateExpenseFragment(itemBudgetFragment)
            }
        }
    }

    private fun notifyText(firstPrice: Int) {
        when (firstPrice) {
            in 0 until averageBudget -> {
                getTextNotify(Constant.NORMAL_INFO_TEXT_TITLE, Constant.NORMAL_INFO_TEXT_CONTENT)
            }
            in averageBudget until averageBudget * 2 -> {
                getTextNotify(Constant.LOTS_INFO_TEXT_TITLE, Constant.LOTS_INFO_TEXT_CONTENT)
            }
            else -> {
                getTextNotify(Constant.CRAZY_INFO_TEXT_TITLE, Constant.CRAZY_INFO_TEXT_CONTENT)
            }
        }
    }

    private fun getTextNotify(title: String, description: String) {
        binding.tvTitleCurrentBudget.text = title
        binding.tvContentCurrentBudget.text = description
    }


}