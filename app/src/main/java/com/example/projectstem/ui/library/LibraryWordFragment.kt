package com.example.projectstem.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projectstem.databinding.FragmentLibraryBinding
import com.example.projectstem.dictionary.Base
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

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

    fun getWordDefinition(word: String, language: String)
    {
        val languageCode = getLanguageCode(language)
        val url = "https://api.dictionaryapi.dev/api/v2/entries/$languageCode/$word";
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val base = gson.fromJson(body, Array<Base>::class.java).toList()
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

        })
    }


    private fun getLanguageCode(language: String): String {
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
        return "Sorry, we don't support word definition in this language"
    }
}