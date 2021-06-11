package com.example.projectstem.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.R
import com.example.projectstem.model.group.GroupViewModel
import com.example.projectstem.model.testdb.TestListAdapter

class TestFragment : Fragment() {

    private lateinit var groupViewModel: GroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_test, container, false)

        val baseLanguage = view.findViewById<EditText>(R.id.language1)
        val secondaryLanguage = view.findViewById<EditText>(R.id.language2)

        //Insert group language to database
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)


        view.findViewById<Button>(R.id.addLanguageGroup).setOnClickListener {
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
                Navigation.findNavController(view).navigate(R.id.testFragment)
            }
        }
        // Recyclerview
        val adapter = TestListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)
        groupViewModel.getAllLanguageGroups.observe(viewLifecycleOwner, Observer { group ->
            adapter.setData(group)
        })
        return view
    }
}