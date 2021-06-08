package com.example.projectstem

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.projectstem.databinding.ActivityMainBinding
import com.example.projectstem.ui.home.HomeFragment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStreamWriter

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

    fun save(view: View) {
        val path = this.getExternalFilesDir(null)
        val letDirectory = File(path, "stem")
        letDirectory.mkdirs()
        val file = File(letDirectory, "savedWords.txt")
        val str: String = getString(R.string.app_name)
        // val array: Array<String> = resources.getStringArray(R.array.CategoryListOfWords)

        FileOutputStream(file).bufferedWriter().use { it.write(str) }

        // FileOutputStream(file).bufferedWriter().use { it.write(text)}

        Toast.makeText(this, "Saved to " + path + "/" + file.name, Toast.LENGTH_LONG).show()
    }


    /*   //Fragment.library: clicking on the button
       protected fun onCreateByClick(savedValues: Bundle) {
           val button: Button = findViewById(R.id.button)
           button.setOnClickListener(this)
           byClick()
       }

       // Implement the OnClickListener callback
       fun byClick() {
           Toast.makeText(this, "You clicked on me!", Toast.LENGTH_SHORT).show()
       }*/
}
