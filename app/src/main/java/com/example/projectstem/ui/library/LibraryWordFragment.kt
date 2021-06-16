package com.example.projectstem.ui.library

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.projectstem.R
import com.example.projectstem.dictionary.Base
import com.example.projectstem.model.AppDatabase
import com.example.projectstem.model.word.WordListAdapter
import com.example.projectstem.model.word.WordViewModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_library_word.*
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
        val knowledge = response.getKnowledge()?.toInt()
        val id =  AppDatabase.getDatabase(requireContext()).groupDao().findById(language)


        //deleting the word
        val wordId = response.getWord()
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        view.findViewById<Button>(R.id.bDeleteWord).setOnClickListener{
            wordViewModel.deleteWord(wordId!!.toInt())
            Navigation.findNavController(view).navigate(R.id.action_libraryWordFragment_to_navigation_library)
        }
        val languageCode = getLanguageCode(id)
        if(languageCode == "X") {
            view.findViewById<TextView>(R.id.tvWordO).visibility = View.GONE
            view.findViewById<TextView>(R.id.partOfSpeech).visibility = View.GONE
            view.findViewById<TextView>(R.id.tvPhonetic).visibility = View.GONE
            view.findViewById<TextView>(R.id.definition).visibility = View.GONE
            view.findViewById<TextView>(R.id.example).visibility = View.GONE
            view.findViewById<TextView>(R.id.tvNote).visibility = View.GONE
            view.findViewById<TextView>(R.id.tvNote2).visibility = View.GONE
            view.findViewById<TextView>(R.id.tvTranslation).visibility = View.GONE
            view.findViewById<TextView>(R.id.translationWord).visibility = View.GONE
            view.findViewById<TextView>(R.id.example).visibility = View.GONE
            view.findViewById<Button>(R.id.bPronounciation).visibility = View.GONE
            view.findViewById<TextView>(R.id.notSupported).visibility = View.VISIBLE
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
                        when (knowledge) {
                            1 -> {
                                view.findViewById<ImageView>(R.id.knowledgeImg1).visibility = View.VISIBLE
                            }
                            2 -> {
                                view.findViewById<ImageView>(R.id.knowledgeImg2).visibility = View.VISIBLE
                            }
                            else -> {
                                view.findViewById<ImageView>(R.id.knowledgeImg3).visibility = View.VISIBLE
                            }
                        }
                    })
                }
                override fun onFailure(call: Call, e: IOException) {
                    println("Failed to execute request")
                }

            })


            /**
             * Play pronunciation for a word if supported
             */
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

    private fun checkKnowledge(knowledge: Int)
    {
    }

    private fun applyAudio(url: String)
    {
        MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepare()
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