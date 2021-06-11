package com.example.projectstem.ui.library

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.projectstem.R
import androidx.navigation.Navigation

class LibraryCategoryFragment : Fragment() {

    companion object {
        fun newInstance() = LibraryCategoryFragment()
    }

    private lateinit var viewModel: LibraryCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library_category, container, false)
        val buttonAddWord = view?.findViewById<Button>(R.id.addBtn)
        // TODO: Get languages from specific group and apply them
        buttonAddWord?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigation_translate)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LibraryCategoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}