package com.example.projectstem.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.projectstem.R
import com.example.projectstem.model.Group
import com.example.projectstem.model.group.GroupViewModel


class LibraryHover: Fragment() {
    private lateinit var groupViewModel: GroupViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_library_hover,container, false)
/*
    Custom code
 */
        val baseLanguage = view.findViewById<AutoCompleteTextView>(R.id.baseLanguage)
        val secondaryLanguage = view.findViewById<AutoCompleteTextView>(R.id.secondaryLanguage)
//cancel button
        view.findViewById<Button>(R.id.bCancel).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigation_library)
        }
        val languages = resources.getStringArray(R.array.lCategory)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item, languages)
        view.findViewById<AutoCompleteTextView>(R.id.baseLanguage).setAdapter(arrayAdapter)
        view.findViewById<AutoCompleteTextView>(R.id.secondaryLanguage).setAdapter(arrayAdapter)

/*
database coding
 */



        //Insert group language to database
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)


        view.findViewById<Button>(R.id.bCreate).setOnClickListener {
            val language1 = baseLanguage.text.toString()
            val language2 = secondaryLanguage.text.toString()
            var isInserted = false;
            //add data
            if(!isInserted)
            {
                val group = Group(0, language1, language2);
                groupViewModel.addLanguageGroup(group)
                isInserted = true;
            }
            if(isInserted)
            {
                Toast.makeText(requireContext(),"Successfully added", Toast.LENGTH_LONG).show()
                Navigation.findNavController(view).navigate(R.id.navigation_library)
            }
        }
        return view

    }

    private fun insertLanguageGroupToDb() {

    }



}