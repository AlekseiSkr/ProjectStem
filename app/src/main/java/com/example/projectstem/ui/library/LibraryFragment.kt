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
import com.example.projectstem.model.group.GroupListAdapter
import com.example.projectstem.model.group.GroupViewModel

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


        /**
         * onClick listener on the @buttonLang button in the fragment_library page to add a word.
         */
        val buttonLang = view.findViewById<Button>(R.id.button_lang)
        buttonLang?.setOnClickListener {
        Navigation.findNavController(view).navigate(R.id.action_grp2hvr)
        }

        //recyclerview

        /**
         * supply the context and define the adapter which will have the configuration logic. The RecyclerView library dynamically creates the elements when they're needed.
         * RecyclerView reuses the view for new items that have scrolled onscreen. This reuse vastly improves performance, improving the app's responsiveness and reducing power consumption.
         */
        val gridLayout = GridLayoutManager(requireContext(),2)
        val adapter = GroupListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvGroups)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = gridLayout

        // UserViewModel
        /**
         * groupViewModel follows the observer pattern. It notifies Observer objects when underlying data changes.
         * It can consolidate the code to update the UI in these Observer objects. That way, we don't need to update the UI every time the app data changes because the observer does it for us.
         */
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)
        groupViewModel.getAllLanguageGroups.observe(viewLifecycleOwner, Observer { group ->
            adapter.setData(group)
        })

        return view
    }
}