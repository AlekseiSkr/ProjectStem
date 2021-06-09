package com.example.projectstem.ui.library

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectstem.databinding.FragmentLibraryBinding

class LibraryWordFragment : Fragment() {

    private lateinit var libraryViewModel: LibraryWordViewModel
    private var _binding: FragmentLibraryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        libraryViewModel =
            ViewModelProvider(this).get(LibraryWordViewModel::class.java)

        _binding = FragmentLibraryBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getLanguageCode(language: String): String {
        when(language)
        {
            "English" -> {
                return "en_US"
            }
            "Hindi" -> {
                return "hi"
            }
            "Spanish" -> {
                return "es"
            }
            "French" -> {
                return "fr"
            }
            "Japanese" -> {
                return "ja"
            }
            "Russian" -> {
                return "ru"
            }
            "German" -> {
                return "it"
            }
            "Korean" -> {
                return "ko"
            }
            "Brazilian" -> {
                return "pt-BR"
            }
            "Portuguese" -> {
                return "pt-BR"
            }
            "Arabic" -> {
                return "ar"
            }
            "Turkish" -> {
                return "tr"
            }
        }
        return "Language code not found"
    }

    fun getWordInformation(languageCode: String, word: String)
    {

    }
}