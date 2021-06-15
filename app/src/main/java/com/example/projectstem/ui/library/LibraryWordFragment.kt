package com.example.projectstem.ui.library

import android.annotation.SuppressLint
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.session.MediaSession
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.projectstem.R
import com.example.projectstem.dictionary.Base
import com.example.projectstem.model.AppDatabase
import com.example.projectstem.model.group.GroupViewModel
import com.example.projectstem.model.testdb.WordListAdapter
import com.example.projectstem.model.word.WordViewModel
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class LibraryWordFragment : Fragment() {
    private lateinit var wordViewModel: WordViewModel
    private lateinit var base: List<Base>
    private lateinit var url: String
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_library_word,container, false)

        val response = arguments?.getSerializable("wordAndLanguage") as WordListAdapter.WordAndLanguage
        val word = response.getOriginal()
        val language = response.getLanguageGroupId()?.toInt()
        val id =  AppDatabase.getDatabase(requireContext()).groupDao().findById(language)


        //deleting the word
        val wordId = response.getWord()
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        view.findViewById<Button>(R.id.bDeleteWord).setOnClickListener{
            wordViewModel.deleteWord(wordId!!.toInt())
            Navigation.findNavController(view).navigate(R.id.navigation_library_category)
        }
        val languageCode = getLanguageCode(id)
        if(languageCode == "X") {
              // We need to navigate back to category page
            Toast.makeText(context, "We don't have word definition feature for this language yet", Toast.LENGTH_SHORT).show()
          //  Navigation.findNavController(view).navigate(R.id.navigation_library)
        } else {
            view.findViewById<TextView>(R.id.translationWord).text = response.getTranslation().toString()
            url = "https://api.dictionaryapi.dev/api/v2/entries/$languageCode/$word";
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    println(body)
                    val gson = GsonBuilder().create()
                    base = gson.fromJson(body, Array<Base>::class.java).toList()

                    activity?.runOnUiThread(java.lang.Runnable {
                        view.findViewById<TextView>(R.id.tvWordO).text = base[0].word
                        view.findViewById<TextView>(R.id.partOfSpeech).text =
                            base[0].meanings[0].partOfSpeech
                        view.findViewById<TextView>(R.id.tvPhonetic).text =
                            base[0].phonetics[0].text
                        view.findViewById<TextView>(R.id.definition).text =
                            base[0].meanings[0].definitions[0].definition
                        view.findViewById<TextView>(R.id.example).text =
                            base[0].meanings[0].definitions[0].example
                    })
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to execute request")
                }

            })

            view.findViewById<Button>(R.id.bPronounciation).setOnClickListener {
                if (languageCode == "en_US") {
                    url = base[0].phonetics[0].audio
                    applyAudio(url)
                } else {
                    Toast.makeText(
                        context,
                        "We don't have audio feature for this language yet",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return view
    }

    private fun applyAudio(url: String)
    {
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }

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
        return "X"
    }
}