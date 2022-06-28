package gam.trainingcourse.bottomnavigationandviewpager2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import gam.trainingcourse.bottomnavigationandviewpager2.R
import gam.trainingcourse.bottomnavigationandviewpager2.adapter.DemoViewPagerAdapter
import gam.trainingcourse.bottomnavigationandviewpager2.transform.DepthPageTransformer
import gam.trainingcourse.bottomnavigationandviewpager2.transform.ZoomOutPageTransformer
import gam.trainingcourse.bottomnavigationandviewpager2.utils.Constant

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2Demo : ViewPager2
    private lateinit var bottomNavigationDemo : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2Demo = findViewById(R.id.viewPager2Demo)
        bottomNavigationDemo = findViewById(R.id.bottomNavigationDemo)

        val demoViewPagerAdapter: DemoViewPagerAdapter = DemoViewPagerAdapter(this)
        viewPager2Demo.adapter = demoViewPagerAdapter
        viewPager2Demo.setPageTransformer(ZoomOutPageTransformer())

        bottomNavigationDemo.setOnItemSelectedListener{
            when (it.itemId){
                R.id.action_beverage_nv -> {
                    viewPager2Demo.currentItem = Constant.FRAGMENT_Demo1Fragment
                }
                R.id.action_handyman_nv -> {
                    viewPager2Demo.currentItem = Constant.FRAGMENT_Demo2Fragment
                }
                else -> {
                    viewPager2Demo.currentItem = Constant.FRAGMENT_Demo3Fragment
                }
            }
            true
        }

        viewPager2Demo.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){

            private var mState = 0
            private var currentPosition = Constant.FRAGMENT_Demo1Fragment

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (mState == ViewPager2.SCROLL_STATE_DRAGGING
                    && currentPosition == position){
                    if (currentPosition == Constant.FRAGMENT_Demo1Fragment){
                        //scroll page0 -> page(n-1)
                        viewPager2Demo.currentItem = Constant.FRAGMENT_Demo3Fragment
                    }
                    else{
                        //scroll page(n-1) -> page(0)
                        viewPager2Demo.currentItem = Constant.FRAGMENT_Demo1Fragment
                    }
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                when (position){
                    Constant.FRAGMENT_Demo1Fragment -> {
                        bottomNavigationDemo.menu.findItem(R.id.action_beverage_nv).isChecked = true
                    }
                    Constant.FRAGMENT_Demo2Fragment -> {
                        bottomNavigationDemo.menu.findItem(R.id.action_handyman_nv).isChecked = true
                    }
                    Constant.FRAGMENT_Demo3Fragment -> {
                        bottomNavigationDemo.menu.findItem(R.id.action_headset_nv).isChecked = true
                    }
                }
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                mState = state
                super.onPageScrollStateChanged(state)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_zoom){
            viewPager2Demo.setPageTransformer(ZoomOutPageTransformer())
        }else if (id == R.id.menu_depth){
            viewPager2Demo.setPageTransformer(DepthPageTransformer())
        }
        return true
    }

    override fun onBackPressed() {
        if (viewPager2Demo.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager2Demo.currentItem = viewPager2Demo.currentItem - 1
        }
    }
}