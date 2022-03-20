package com.fegusta.abcapacity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fegusta.abcapacity.adapter.QuestAdapter
import com.fegusta.abcapacity.fragments.AddQuestFragment
import com.fegusta.abcapacity.fragments.HomeFragment
import com.fegusta.abcapacity.fragments.ProfileFragment
import com.fegusta.abcapacity.fragments.SettingsFragment
import com.fegusta.abcapacity.repository.QuestRepository
import com.fegusta.abcapacity.ui.CadastroQuestActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var homeFragment: HomeFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var settingsFragment: SettingsFragment
    private lateinit var addQuestFragment: AddQuestFragment

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
    }

    private fun initViews() {
        homeFragment = HomeFragment()
        profileFragment = ProfileFragment()
        settingsFragment = SettingsFragment()
        addQuestFragment = AddQuestFragment()

        bottomNavigation = findViewById(R.id.bottom_navigation)
    }

    private fun initListeners() {
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main -> {
                setFragment(homeFragment)
            }
            R.id.menu_profile -> {
                setFragment(profileFragment)
            }
            R.id.menu_settings -> {
                setFragment(settingsFragment)
            }
            R.id.menu_add_quest -> {
                setFragment(addQuestFragment)
            }
        }
        return true
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}