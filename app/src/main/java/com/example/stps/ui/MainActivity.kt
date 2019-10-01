package com.example.stps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val PARAM_NAVIGATION_ID = "navigation_id"

        fun newInstance(context: Context, navigationId: Int) = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(PARAM_NAVIGATION_ID, navigationId)
        }
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_photo -> {
//                loadFragment(item.itemId)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_album -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user -> {
                return@OnNavigationItemSelectedListener true
            }
        }

//        loadFragment(item.itemId)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//
//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

//        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
//        // loadFragment(navigation.selectedItemId)
//        // loadFragment(R.id.navigation_dashboard)
//        val navigationId = intent.getIntExtra(PARAM_NAVIGATION_ID, R.id.navigation_dashboard)
//        navigation.selectedItemId = navigationId
    }

//    private fun loadFragment(itemId: Int) {
//        val tag = itemId.toString()
//        var fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
//            R.id.navigation_home -> {
//                TestFragment.newInstance()
//            }
//            R.id.navigation_dashboard -> {
//                Test2Fragment.newInstance()
//            }
//            R.id.navigation_notifications -> {
//                Test3Fragment.newInstance()
//            }
//            else -> {
//                null
//            }
//        }
//
//        // replace fragment
//        if (fragment != null) {
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.fragmentContainer, fragment, tag)
//                .commit()
//        }
//    }
}
