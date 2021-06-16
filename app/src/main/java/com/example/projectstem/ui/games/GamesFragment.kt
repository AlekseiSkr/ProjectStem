package com.example.projectstem.ui.games

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projectstem.R
import com.example.projectstem.databinding.FragmentGamesBinding
import com.example.projectstem.model.AppDatabase
import com.example.projectstem.model.Group
import com.example.projectstem.model.group.GroupViewModel

class GamesFragment : Fragment() {

    private lateinit var gamesViewModel: GamesViewModel
    private lateinit var groupViewModel: GroupViewModel
    private var _binding: FragmentGamesBinding? = null
    private var groupId: Int = 0

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


        gamesViewModel = ViewModelProvider(this).get(GamesViewModel::class.java)

        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // var languageGroups =  ArrayList<Group>()

        val groups = AppDatabase.getDatabase(requireContext()).groupDao().getAllGroupsForGames()
        var languageGroups =  ArrayList<Group>()
        for (group in groups) {
            //languageGroups.add(" ${group.language1} ${group.language2} ")
            if (groups.isNotEmpty()) {
                languageGroups.add(group)
            }else{
                Toast.makeText(
                    requireContext(),
                    "Please Create language Groups", Toast.LENGTH_LONG
                ).show()
            }
        }

            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, languageGroups)
                Log.d("GamesFrag", "line 1: ${languageGroups[0]}")

        val autoCompTextView = binding.autoCompleteTextView2
        autoCompTextView.setAdapter(arrayAdapter)

        val b = Bundle()

        var selectedGroup: Group? = null
        autoCompTextView.setOnItemClickListener { parent, view, position, id ->
            selectedGroup = parent.getItemAtPosition(position) as Group
            groupId = selectedGroup!!.group_id
            binding.textView2.visibility = View.VISIBLE
            binding.lang1.text = selectedGroup!!.language1
            binding.lang2.text = selectedGroup!!.language2
            binding.lang1.visibility = View.VISIBLE
            binding.lang2.visibility = View.VISIBLE
            loadBundle(groupId, b)
        }

        b.putInt("grpId", groupId)

        binding.flashCards.setOnClickListener{
            if (b.getInt("grpId", groupId) !=0 ){
                val intent = Intent(context, QuizQuestionsActivity::class.java)
                intent.putExtras(b)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(),
                    "Please Select a language group with words in it", Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.hangman.setOnClickListener {
            if (b.getInt("grpId", groupId) !=0 ){
                val intent = Intent(context, HangmanGameActivity::class.java)
                intent.putExtras(b)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(),
                "Please Select a language group with words in it", Toast.LENGTH_LONG
                ).show()
            }
        }
        return root
    }

    fun loadBundle(data: Int, bundle: Bundle){

        if (data != 0) {
            bundle.putInt("grpId", data)
        }else{
            Toast.makeText(requireContext(),
                "Please Select a language group", Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}