package com.example.projectstem.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.projectstem.databinding.FragmentLibraryBinding
import com.example.projectstem.R



class LibraryHover:Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.fragment_library_hover,container, false)
/*
    Custom code
 */
        val buttonLang = view?.findViewById<Button>(R.id.bCancel)
            buttonLang!!.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_hvr2grp)
            }
        val buttonLang2 = view?.findViewById<Button>(R.id.bCreate)
        buttonLang2!!.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_hvr2grp)
        }

        val languages = resources.getStringArray(R.array.lCategory)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item, languages)
        view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView2).setAdapter(arrayAdapter)
        view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView).setAdapter(arrayAdapter)


        return view
    }
}