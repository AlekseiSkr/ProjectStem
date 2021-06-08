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
import com.google.gson.Gson
import com.google.gson.GsonBuilder


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
        // Save txt file
//        val path = this.getExternalFilesDir(null)
//        val letDirectory = File(path, "stem")
//        letDirectory.mkdirs()
//        val file = File(letDirectory, "savedWords.txt")
//        val str: String = getString(R.string.app_name)
//
//        FileOutputStream(file).bufferedWriter().use { it.write(str) }
//        Toast.makeText(this, "Saved to " + path + "/" + file.name, Toast.LENGTH_LONG).show()

        // Save json file
        // Accessible files via View -> Tool Windows -> Device File Explorer (while running emulator) -> storage/emulated/0/Android/data/com.example.projectstem/files/stem
        val array: Array<String> = resources.getStringArray(R.array.CategoryListOfWords)

        val path = this.getExternalFilesDir(null)
        val letDirectory = File(path, "stem")
        letDirectory.mkdirs()
        val file = File(letDirectory, "savedWords.json")

        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val arrayToList = gsonPretty.toJson(array)

        val arrayToPretty: String = gsonPretty.toJson(arrayToList)
        file.writeText(arrayToPretty)

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
