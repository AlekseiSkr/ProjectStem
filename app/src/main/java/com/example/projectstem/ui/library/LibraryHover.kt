package com.example.projectstem.ui.library

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectstem.R
import com.example.projectstem.model.Group
import com.example.projectstem.model.Word
import com.example.projectstem.model.group.GroupViewModel
import com.example.projectstem.model.testdb.TestListAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File


class LibraryHover: Fragment() {

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

        val languages = resources.getStringArray(R.array.lCategory)
        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item, languages)
        view.findViewById<AutoCompleteTextView>(R.id.baseLanguage).setAdapter(arrayAdapter)
        view.findViewById<AutoCompleteTextView>(R.id.secondaryLanguage).setAdapter(arrayAdapter)

        //TEST

        view.findViewById<Button>(R.id.testButton).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.testFragment)
        }

        //convert input to json on click
        view.findViewById<Button>(R.id.bCreate).
        setOnClickListener(View.OnClickListener {

            //Toast with values
            val enteredText = secondaryLanguage.text.toString() + " " + baseLanguage.text.toString()
            Toast.makeText(requireContext(), enteredText, Toast.LENGTH_SHORT).show()
            //Group created
            val group = Group(1, secondaryLanguage.text.toString(), baseLanguage.text.toString())
            appendJsonFile(convertToJson(group))
        })

        return view

    }

    private fun insertLanguageGroupToDb() {

    }

    /**
     * Saving groups
     *
     * TODO:
     * Get number of existing groups from Library Fragment via Intent
     * Number of existing groups + 1 = new group ID
     */
    private fun convertToJson(group: Group): String {

        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val json_group: String = gsonPretty.toJson(group)

        //Debug
        Log.d("saveJson", "\n" + json_group)

        return json_group
    }

    private fun appendJsonFile(group: String){
        val path = File("/storage/emulated/0/Android/data/com.example.projectstem/files/stem/stem_groups.json")
        path.length()

        path.appendText(group.plus(","))

    }

    private fun callJson(){
        //
    }


}