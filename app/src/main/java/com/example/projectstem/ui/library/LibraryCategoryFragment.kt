package com.example.projectstem.ui.library

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.projectstem.R
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.model.group.GroupListAdapter
import com.example.projectstem.model.group.GroupViewModel
import com.example.projectstem.model.testdb.WordListAdapter
import com.example.projectstem.model.word.WordViewModel
import org.w3c.dom.Text

class LibraryCategoryFragment : Fragment() {
    private lateinit var groupViewModel: GroupViewModel
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
        buttonAddWord?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigation_translate)
        }
        fun validateLoaded(): Boolean{
            return true
        }
        

        val response = arguments?.getSerializable("idAndLanguage") as GroupListAdapter.IdAndLanguage
        val id = response.getId()
        val l1 = response.getLanguageFirst()
        val l2 = response.getLanguageSecond()

        view.findViewById<TextView>(R.id.languageGroupCatID).text = id
        view.findViewById<TextView>(R.id.languageCat1).text = l1
        view.findViewById<TextView>(R.id.languageCat2).text = l2

        //recyclerView connection
        val linearLayout = LinearLayoutManager(requireContext())
        val adapter = WordListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.catWords)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = linearLayout


        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        if (id != null) {
            wordViewModel.getWordsFromGroup(id.toInt()).observe(viewLifecycleOwner, Observer { word ->
                adapter.setData(word)
            })

        }//delete the group
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)
        view.findViewById<Button>(R.id.bDeleteGroup).setOnClickListener{
            groupViewModel.deleteByGroupId(id!!.toInt())
            Navigation.findNavController(view).navigate(R.id.navigation_library)
        }
        return view
    }
}