package com.example.projectstem.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.projectstem.R
import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class LibraryFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

       val view = inflater.inflate(R.layout.fragment_library,container, false)

        view?.findViewById<LinearLayout>(R.id.something)!!.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_grp2cat)
        }

        val buttonLang = view.findViewById<Button>(R.id.button_lang)
        buttonLang?.setOnClickListener {
        Navigation.findNavController(view).navigate(R.id.navigation_library_hover)
        }

        return view
    }

}