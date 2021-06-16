package com.example.projectstem.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectstem.R
import com.example.projectstem.model.group.GroupListAdapter
import com.example.projectstem.model.group.GroupViewModel
import com.example.projectstem.model.word.WordListAdapter
import com.example.projectstem.model.word.WordViewModel
import java.io.Serializable

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

        //changing the view based on information gotten from the group
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

        }

        //bundling the languages for the new word button
        val buttonAddWord = view?.findViewById<Button>(R.id.addBtn)
        val languages = Languages()
        languages.setFirstLanguage(view.findViewById<TextView>(R.id.languageCat1).text.toString())
        languages.setSecondLanguage(view.findViewById<TextView>(R.id.languageCat2).text.toString())
        val b = Bundle()
        b.putSerializable("languages", languages)
        //add new word button
        buttonAddWord?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigation_translate,b)
        }

        fun validateLoaded(): Boolean{
            return true
        }

        //delete the group
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)
        view.findViewById<Button>(R.id.bDeleteGroup).setOnClickListener{
            groupViewModel.deleteByGroupId(id!!.toInt())
            Navigation.findNavController(view).navigate(R.id.action_libraryCategoryFragment_to_navigation_library)
        }
        return view
    }
    class Languages : Serializable{

        private lateinit var languageFirst: String
        private lateinit var languageSecond: String

        fun getFirstLanguage():String?{
            return languageFirst
        }
        fun getSecondLanguage():String?{
            return languageSecond
        }
        fun setFirstLanguage(firstLanguage :String){
            this.languageFirst = firstLanguage!!
        }
        fun setSecondLanguage(secondLanguage: String){
            this.languageSecond = secondLanguage!!
        }
    }
}