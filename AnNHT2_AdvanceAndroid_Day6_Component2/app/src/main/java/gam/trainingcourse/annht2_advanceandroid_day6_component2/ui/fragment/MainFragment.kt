package gam.trainingcourse.annht2_advanceandroid_day6_component2.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import gam.trainingcourse.annht2_advanceandroid_day6_component2.databinding.FragmentMainBinding
import gam.trainingcourse.annht2_advanceandroid_day6_component2.model.User
import android.view.*
import com.google.android.material.tabs.TabLayoutMediator
import gam.trainingcourse.annht2_advanceandroid_day6_component2.viewpager_adapter.BottomViewPagerAdapter
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.viewpager2.widget.ViewPager2

import com.google.android.material.navigation.NavigationView
import gam.trainingcourse.annht2_advanceandroid_day6_component2.R

class MainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    val args: MainFragmentArgs by navArgs()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object{
        var FRAG_HOME = 0
        var FRAG_GAMES = 1
        var FRAG_MOVIES = 2
        var FRAG_BOOK = 3
        var FRAG_MUSIC = 4
    }

    private var mCurrentFrg = FRAG_HOME

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
//        val toggle = ActionBarDrawerToggle(requireActivity(), binding.drawerLayoutForBottom
//            , binding.toolbar,R.string.open_navigation_drawer, R.string.close_navigation_drawer)
//        binding.drawerLayoutForBottom.addDrawerListener(toggle)
//        toggle.syncState()

        val user: User = args.loginUserInfo
        Log.d("user_login", user.toString())
        val headerView: View = binding.navBottomTab.getHeaderView(0)
        val navUsername : TextView =
            headerView.findViewById(R.id.username_nav)
        val navPass : TextView =
            headerView.findViewById(R.id.password_nav)
        navUsername.text = user.username
        navPass.text = user.password

        val bottomViewPagerAdapter = BottomViewPagerAdapter(requireActivity())

        binding.viewPager2Movies.adapter = bottomViewPagerAdapter
        binding.viewPager2Movies.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayoutBottom, binding.viewPager2Movies
        ) { tab, position -> when (position){
            0 -> {
                tab.text = getString(R.string.home)
                tab.setIcon(R.drawable.ic_home)
            }
            1 -> {
                tab.text = getString(R.string.games)
                tab.setIcon(R.drawable.ic_baseline_games_24)
            }
            2 -> {
                tab.text = getString(R.string.movies)
                tab.setIcon(R.drawable.ic_movie)
            }
            3 -> {
                tab.text = getString(R.string.books)
                tab.setIcon(R.drawable.ic_baseline_book_24)
            }
            4 -> {
                tab.text = getString(R.string.music)
                tab.setIcon(R.drawable.ic_baseline_music_note_24)
            }
        }}.attach()

        binding.navBottomTab.setNavigationItemSelectedListener(this)

        binding.navBottomTab.menu
            .findItem(R.id.nav_home).isChecked = true

        binding.viewPager2Movies.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position){
                    0 -> {
                        mCurrentFrg = FRAG_HOME
                        binding.navBottomTab.menu.findItem(R.id.nav_home).isChecked = true
                    }
                    1 -> {
                        mCurrentFrg = FRAG_GAMES
                        binding.navBottomTab.menu.findItem(R.id.nav_game).isChecked = true
                    }
                    2 -> {
                        mCurrentFrg = FRAG_MOVIES
                        binding.navBottomTab.menu.findItem(R.id.nav_movies).isChecked = true
                    }
                    3 -> {
                        mCurrentFrg = FRAG_BOOK
                        binding.navBottomTab.menu.findItem(R.id.nav_books).isChecked = true
                    }
                    4 -> {
                        mCurrentFrg = FRAG_MUSIC
                        binding.navBottomTab.menu.findItem(R.id.nav_music).isChecked = true
                    }
                }
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.nav_home -> {
                if (mCurrentFrg != FRAG_HOME){
                    mCurrentFrg = FRAG_HOME
                    binding.viewPager2Movies.currentItem = 0
                }
            }
            R.id.nav_game -> {
                if (mCurrentFrg != FRAG_GAMES){
                    mCurrentFrg = FRAG_GAMES
                    binding.viewPager2Movies.currentItem = 1
                }
            }
            R.id.nav_movies -> {
                if (mCurrentFrg != FRAG_MOVIES){
                    mCurrentFrg = FRAG_MOVIES
                    binding.viewPager2Movies.currentItem = 2
                }
            }
            R.id.nav_books -> {
                if (mCurrentFrg != FRAG_BOOK){
                    mCurrentFrg = FRAG_BOOK
                    binding.viewPager2Movies.currentItem = 3
                }
            }
            R.id.nav_music -> {
                if (mCurrentFrg != FRAG_MUSIC){
                    mCurrentFrg = FRAG_MUSIC
                    binding.viewPager2Movies.currentItem = 4
                }
            }
        }

        binding.drawerLayoutForBottom.closeDrawer(GravityCompat.START)

        return true
    }
}