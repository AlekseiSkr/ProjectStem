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



        val view = inflater.inflate(R.layout.fragment_library,container, false)
/*
    Custom code
 */

        val autoCompleteTextView: AutoCompleteTextView = view.findViewById(R.id.autoCompleteTextView)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lCategory,
            android.R.layout.simple_dropdown_item_1line
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            autoCompleteTextView.setAdapter(adapter)
        }
        val autoCompleteTextView2: AutoCompleteTextView = view.findViewById(R.id.autoCompleteTextView2)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lCategory,
            android.R.layout.simple_dropdown_item_1line
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            autoCompleteTextView2.setAdapter(adapter)
        }


            val buttonLang = view?.findViewById<Button>(R.id.bCancel)
            buttonLang?.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_hvr2grp)
            }




        return view
    }
}