package info.spreadcode.bottomnavigationview

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.android.synthetic.main.activity_bottom_navigation.*

class BottomNavigationActivity : AppCompatActivity(),
    NavigationBarView.OnItemSelectedListener {

    private val homeFragment = HomeFragment()
    private val trendingFragment = TrendingFragment()
    private val favouriteFragment = FavouriteFragment()

    private lateinit var activeFragment: Fragment

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        // Set up initial value to views and add views to listener
        setUpViewsAndListeners()

        // Set up initial fragment transaction
        setUpFragmentTransaction()


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        supportActionBar!!.title = item.title
        when (item.itemId) {
            R.id.home_navigation -> {
                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                activeFragment = homeFragment
                return true
            }

            R.id.trending_navigation -> {
                fragmentManager.beginTransaction().hide(activeFragment).show(trendingFragment)
                    .commit()
                activeFragment = trendingFragment
                return true
            }
            R.id.favourite_navigation -> {
                fragmentManager.beginTransaction().hide(activeFragment).show(favouriteFragment)
                    .commit()
                activeFragment = favouriteFragment
                return true
            }

        }
        return false
    }

    /**
     * Set up initial value to views and attach views to listener
     */
    private fun setUpViewsAndListeners() {

        // Assign first fragment to the active fragment
        activeFragment = homeFragment

        // Attach bottom navigation view to listener
        bottom_navigation_view.setOnItemSelectedListener(this)

        // set first fragment title to the toolbar title
        supportActionBar!!.title = getString(R.string.home_fragment_bottom_navigation_title)
    }

    /**
     * Attach fragment to the FragmentManager, hide and commit
     */
    private fun setUpFragmentTransaction() {

        fragmentManager.beginTransaction().add(R.id.fragment_container, favouriteFragment, "3")
            .hide(favouriteFragment).commit()

        fragmentManager.beginTransaction().add(R.id.fragment_container, trendingFragment, "2")
            .hide(trendingFragment).commit()

        fragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment, "1").commit()
    }

}
