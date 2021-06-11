package com.example.projectstem.ui.library

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.example.projectstem.databinding.FragmentLibraryBinding
import com.google.gson.JsonObject
import okhttp3.Call
import okhttp3.Callback
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import okhttp3.OkHttpClient
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
        getWordDefinition()

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
        return "Sorry, we don't support word definition in this language"
    }

    fun getWordDefinition()
    {
        val languageCode = "en_US"
        val word = "hello"
        val url = " https://api.dictionaryapi.dev/api/v2/entries/$languageCode/$word";
        val request = okhttp3.Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body?.string()
                println(body)
            }
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }
        })

    }
}