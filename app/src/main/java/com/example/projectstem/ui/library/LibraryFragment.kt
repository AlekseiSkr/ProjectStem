package com.example.projectstem.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectstem.R
import com.example.projectstem.databinding.FragmentLibraryBinding
import android.content.Intent
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation

class LibraryFragment : Fragment(){

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val message = "You clicked on me!"
        val buttonLang = view?.findViewById<Button>(R.id.button_lang)

        Log.e("Virag", "click1 $view $buttonLang")
        buttonLang?.setOnClickListener {
            Log.e("Virag","click2 $context")
            //Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
           // val intent = Intent(context, LibraryHover::class.java)
           // startActivity(intent)
            Navigation.findNavController(view).navigate(R.id.navigation_library_hover)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}