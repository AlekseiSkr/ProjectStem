package com.example.projectstem

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projectstem.databinding.ActivityMainBinding
/*import android.view.View
import android.widget.Button
import android.widget.Toast*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_library, R.id.navigation_games, R.id.navigation_translate, R.id.navigation_user
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    /*
    //FragmentLibrary: clicking on the button (onClickListener)
    private val button = findViewById<Button>(R.id.button) as Button
    val message = "You clicked on me!"

    fun getClicked(view: View) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }*/
}
