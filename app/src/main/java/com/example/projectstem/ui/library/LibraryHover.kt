package com.example.projectstem.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projectstem.databinding.FragmentLibraryBinding
import com.example.projectstem.R



class LibraryHover:Fragment() {
    private lateinit var gamesViewModel: LibraryViewModel
    private var _binding: FragmentLibraryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gamesViewModel =
            ViewModelProvider(this).get(LibraryViewModel::class.java)

        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root
/*
    Custom code
 */
//        val languages = resources.getStringArray(R.array.lCategory)
        val autoCompleteTextView: AutoCompleteTextView = root.findViewById(R.id.autoCompleteTextView)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lCategory,
            android.R.layout.simple_dropdown_item_1line
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            autoCompleteTextView.setAdapter(adapter)
        }
        val autoCompleteTextView2: AutoCompleteTextView = root.findViewById(R.id.autoCompleteTextView2)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lCategory,
            android.R.layout.simple_dropdown_item_1line
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            autoCompleteTextView2.setAdapter(adapter)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}