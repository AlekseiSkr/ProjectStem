package com.example.projectstem.ui.games

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projectstem.R
import com.example.projectstem.databinding.FragmentGamesBinding
import com.example.projectstem.model.group.GroupViewModel

class GamesFragment : Fragment() {

    private lateinit var gamesViewModel: GamesViewModel
    private lateinit var groupViewModel: GroupViewModel
    private var _binding: FragmentGamesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)
        //array adapter for the dropown list
//        val groups = //INSERT ARRARAYLIST OF GROUPS HERE
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item,groups)
//        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        gamesViewModel = ViewModelProvider(this).get(GamesViewModel::class.java)

        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.flashCards.setOnClickListener{
            val intent = Intent(context, QuizQuestionsActivity::class.java)
            startActivity(intent)
        }

        binding.hangman.setOnClickListener {
            val intent = Intent(context, HangmanGameActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}