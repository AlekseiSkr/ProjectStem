package com.example.projectstem.ui.games

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projectstem.R
import com.example.projectstem.databinding.FragmentGamesBinding
import com.example.projectstem.model.AppDatabase
import com.example.projectstem.model.Group

class GamesFragment : Fragment() {

    private var _binding: FragmentGamesBinding? = null
    private var groupId: Int = 0
    private var groups: List<Group> = emptyList()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        val root: View = binding.root


            groups = AppDatabase.getDatabase(requireContext()).groupDao().getAllGroupsForGames()

            if (groups.isEmpty()) {
                binding.hangman.visibility = View.GONE
                binding.flashCards.visibility = View.GONE
                binding.lang1.visibility = View.GONE
                binding.textView7.text = ("Please Have at least 2 words In your groups to play games!")
                binding.textView7.visibility = View.VISIBLE
                binding.autoCompleteTextView2.visibility = View.GONE
                binding.lang2.visibility = View.GONE
                binding.textInputLayout3.visibility = View.GONE

                Toast.makeText(
                    requireContext(),
                    "Not enough words to play games with", Toast.LENGTH_LONG
                ).show()
            }


        var languageGroups =  ArrayList<Group>()
        for (group in groups) {
            if (groups.isNotEmpty()) {
                languageGroups.add(group)
            }else{
                Toast.makeText(
                    requireContext(),
                    "Please Create language Groups", Toast.LENGTH_LONG
                ).show()
            }
        }


        //Loading Data into the DropDown
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, languageGroups)

        val autoCompTextView = binding.autoCompleteTextView2
        autoCompTextView.setAdapter(arrayAdapter)

        //Initiate Bundle
        val b = Bundle()
        /**
         * This Listener recieves the selected group
         * if loadBundle() is successful, allow navigation to games
         */
        var selectedGroup: Group? = null
        autoCompTextView.setOnItemClickListener { parent, view, position, id ->
            selectedGroup = parent.getItemAtPosition(position) as Group
            groupId = selectedGroup!!.group_id
            binding.textView2.visibility = View.VISIBLE
            binding.lang1.text = selectedGroup!!.language1
            binding.lang2.text = selectedGroup!!.language2
            binding.lang1.visibility = View.VISIBLE
            binding.lang2.visibility = View.VISIBLE

            //if groupId not 0
            if (loadBundle(groupId, b)) {
                binding.flashCards.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.colorado_500))
                binding.hangman.setBackgroundTintList(requireContext().getResources().getColorStateList(R.color.colorado_500))
            }
        }

        /**
         * The listener to navigate to Flash Cards Game
         * Loads the Intent with the groupId
         */
        binding.flashCards.setOnClickListener{
            if (b.getInt("grpId", groupId) !=0 ){
                val intent = Intent(context, QuizQuestionsActivity::class.java)
                intent.putExtras(b)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(),
                    "Please Select a language group with words in it", Toast.LENGTH_SHORT
                ).show()
            }
        }

        /**
         * The listener to navigate to Hangman game
         * Loads the Intent with the groupId
         */
        binding.hangman.setOnClickListener {
            if (b.getInt("grpId", groupId) !=0 ){
                val intent = Intent(context, HangmanGameActivity::class.java)
                intent.putExtras(b)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(),
                "Please Select a language group with words in it", Toast.LENGTH_SHORT
                ).show()
            }
        }
        return root
    }

    /**
     * @Data : Int
     * @Bundle : Bundle
     * @return Boolean
     * On Success Loads the bundle with parsed int Data, return true
     * On fail notify user, return false
     */
    fun loadBundle(data: Int, bundle: Bundle) : Boolean{

        if (data != 0) {
            bundle.putInt("grpId", data)
            return true
        }else{
            Toast.makeText(requireContext(),
                "Please Select a language group", Toast.LENGTH_SHORT
            ).show()
            return false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}