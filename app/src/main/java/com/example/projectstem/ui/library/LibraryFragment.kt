package com.example.projectstem.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.R
import com.example.projectstem.model.group.GroupViewModel
import com.example.projectstem.model.testdb.ListAdapter

class LibraryFragment : Fragment(){
    private lateinit var groupViewModel: GroupViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

       val view = inflater.inflate(R.layout.fragment_library,container, false)


        val groups = view.findViewById<RecyclerView>(R.id.rvGroups)
        //button to go to hover page

        val buttonLang = view.findViewById<Button>(R.id.button_lang)
        buttonLang?.setOnClickListener {
        Navigation.findNavController(view).navigate(R.id.navigation_library_hover)
        }

        /*val groupSelectionButton = view.findViewById<Button>(R.id.groupButton)
        groupSelectionButton?.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.navigation_library_category)
        }*/

        //recyclerview
        val gridLayout = GridLayoutManager(requireContext(),2)
        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvGroups)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = gridLayout

        // UserViewModel
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)
        groupViewModel.getAllLanguageGroups.observe(viewLifecycleOwner, Observer { group ->
            adapter.setData(group)
        })



        return view
    }
}