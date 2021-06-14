package com.example.projectstem.ui.library

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.projectstem.R
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.model.group.GroupListAdapter
import com.example.projectstem.model.group.GroupViewModel
import com.example.projectstem.model.testdb.WordListAdapter
import com.example.projectstem.model.word.WordViewModel

class LibraryCategoryFragment : Fragment() {

    private lateinit var wordViewModel: WordViewModel

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
        fun validateLoaded(): Boolean{
            return true
        }

        //recyclerView connection
        val linearLayout = LinearLayoutManager(requireContext())
        val adapter = WordListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.catWords)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayout

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.getWordsFromGroup(1).observe(viewLifecycleOwner, Observer { word ->
            adapter.setData(word)
        })
        return view
    }


}