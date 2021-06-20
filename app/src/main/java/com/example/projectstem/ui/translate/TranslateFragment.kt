package com.example.projectstem.ui.translate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.projectstem.R
import com.example.projectstem.databinding.FragmentTranslateBinding
import com.example.projectstem.model.AppDatabase
import com.example.projectstem.model.Word
import com.example.projectstem.model.group.GroupViewModel
import com.example.projectstem.model.word.WordViewModel
import com.example.projectstem.ui.library.LibraryCategoryFragment
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage.*
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions


/**
 * This class fragment is responsible for translation feature. Here we translate a word and save it to a chosen group language
 */
class TranslateFragment : Fragment() {

    private lateinit var notificationsViewModel: TranslateViewModel
    private lateinit var wordViewModel: WordViewModel
    private lateinit var groupViewModel: GroupViewModel
    private var _binding: FragmentTranslateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationsViewModel =
            ViewModelProvider(this).get(TranslateViewModel::class.java)

        _binding = FragmentTranslateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //grabbing the languages from the bundle
        val response = arguments?.getSerializable("languages") as? LibraryCategoryFragment.Languages
        val l1 = response?.getFirstLanguage()
        val l2 = response?.getSecondLanguage()
        val saveButton = root.findViewById<Button>(R.id.buttonSaveTranslation)
        val progressBar = root.findViewById<ProgressBar>(R.id.progressBar2)
        saveButton.visibility = View.VISIBLE
        val button = root.findViewById<Button>(R.id.buttonTranslate)
        val translateFromField = root.findViewById<EditText>(R.id.translateFrom)
        val translateToField = root.findViewById<EditText>(R.id.translateTo)


        //Drop down menu of languages - From
        val spinnerFrom: Spinner = root.findViewById(R.id.spinnerFrom)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lCategory,
            R.layout.drop_down
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerFrom.adapter = adapter
            val spinnerPosition = adapter.getPosition(l1)
            spinnerFrom.setSelection(spinnerPosition)
        }
        //Drop down menu of languages - To
        val spinnerTo: Spinner = root.findViewById(R.id.spinnerTo)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lCategory,
            R.layout.drop_down
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerTo.adapter = adapter

            val spinnerPosition = adapter.getPosition(l2)
            spinnerTo.setSelection(spinnerPosition)
        }

        /**
         * On translate button click translate the word given
         */
        button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val translateFromLanguage = spinnerFrom.selectedItem.toString()
            val translateToLanguage = spinnerTo.selectedItem.toString()
            val wordToBeTranslated = translateFromField.text.toString()
            val options = TranslatorOptions.Builder()
                .setSourceLanguage(getLanguageFromDropDownMenu(translateFromLanguage))
                .setTargetLanguage(getLanguageFromDropDownMenu(translateToLanguage))
                .build()
            val translator = Translation.getClient(options)
            val conditions = DownloadConditions.Builder()
                .requireWifi()
                .build()
            // Download translation modules if not present on device
            translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener {
                    translator.translate(wordToBeTranslated)
                            // If modules downloaded successfully translate the word
                        .addOnSuccessListener { translatedText ->
                            translateToField.setText(translatedText)
                            progressBar.visibility = View.INVISIBLE
                        }
                        .addOnFailureListener { exception ->
                            Log.e("s", Log.getStackTraceString(exception))
                        }
                }
                .addOnFailureListener { exception ->
                    Log.e("s", Log.getStackTraceString(exception))
                }
        }

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)

        /**
         * On save button click save word original and word translation to the chosen group language if exists
         */
        saveButton.setOnClickListener {
            if (translateFromField.toString()
                    .isNotEmpty() && translateToField.toString().isNotEmpty()
            ) {
                // Get language group selected with word original and word translation
                val language1 = spinnerFrom.selectedItem.toString()
                val language2 = spinnerTo.selectedItem.toString()
                val translateFrom = translateFromField.text.toString()
                val translateTo = translateToField.text.toString()
                // Initialize database query
                val id = AppDatabase.getDatabase(requireContext()).groupDao()
                    .findByLanguageGroup(language1, language2)

                if (id == 0) {
                    Toast.makeText(
                        requireContext(),
                        "Language group does not exist!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    try {
                        val word = Word(0, id, translateFrom, translateTo, 1)
                        wordViewModel.addWordsToGroup(word)
                        Toast.makeText(
                            requireContext(),
                            "$translateFrom Successfully added to group $language1 / $language2",
                            Toast.LENGTH_LONG
                        ).show()
                        Navigation.findNavController(root).navigate(R.id.navigation_library)
                    } catch (e: Exception) {
                        e.localizedMessage
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please add words to translate",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return root
    }

    /**
     * This method is responsible for getting the language based on the drop down menu
     * @param language The language input from the drop down
     * @return The language from the MLKit API if existent. If not returns X as language not supported
     */
    private fun getLanguageFromDropDownMenu(language: String): String {
        when (language) {
            "Afrikaans" -> {
                return AFRIKAANS
            }
            "Albanian" -> {
                return ALBANIAN
            }
            "Arabic" -> {
                return ARABIC
            }
            "Belarusian" -> {
                return BELARUSIAN
            }
            "Bulgarian" -> {
                return BULGARIAN
            }
            "Bengali" -> {
                return BENGALI
            }
            "Catalan" -> {
                return CATALAN
            }
            "Croatian" -> {
                return CROATIAN
            }
            "Czech" -> {
                return CZECH
            }
            "Danish" -> {
                return DANISH
            }
            "Dutch" -> {
                return DUTCH
            }
            "English" -> {
                return ENGLISH
            }
            "Esperanto" -> {
                return ESPERANTO
            }
            "Estonian" -> {
                return ESTONIAN
            }
            "Finnish" -> {
                return FINNISH
            }
            "French" -> {
                return FRENCH
            }
            "Galician" -> {
                return GALICIAN
            }
            "Georgian" -> {
                return GEORGIAN
            }
            "German" -> {
                return GERMAN
            }
            "Greek" -> {
                return GREEK
            }
            "Gujarati" -> {
                return GUJARATI
            }
            "Haitian" -> {
                return HAITIAN_CREOLE
            }
            "Hebrew" -> {
                return HEBREW
            }
            "Hindi" -> {
                return HINDI
            }
            "Hungarian" -> {
                return HUNGARIAN
            }
            "Icelandic" -> {
                return ICELANDIC
            }
            "Indonesian" -> {
                return INDONESIAN
            }
            "Irish" -> {
                return IRISH
            }
            "Italian" -> {
                return ITALIAN
            }
            "Japanese" -> {
                return JAPANESE
            }
            "Kannada" -> {
                return KANNADA
            }
            "Korean" -> {
                return KOREAN
            }
            "Lithuanian" -> {
                return LITHUANIAN
            }
            "Latvian" -> {
                return LATVIAN
            }
            "Macedonian" -> {
                return MACEDONIAN
            }
            "Marathi" -> {
                return MARATHI
            }
            "Malayalam" -> {
                return MALAY
            }
            "Maltese" -> {
                return MALTESE
            }
            "Norwegian" -> {
                return NORWEGIAN
            }
            "Persian" -> {
                return PERSIAN
            }
            "Polish" -> {
                return POLISH
            }
            "Portuguese" -> {
                return PORTUGUESE
            }
            "Romanian" -> {
                return ROMANIAN
            }
            "Russian" -> {
                return RUSSIAN
            }
            "Slovak" -> {
                return SLOVAK
            }
            "Slovenian" -> {
                return SLOVENIAN
            }
            "Spanish" -> {
                return SPANISH
            }
            "Swedish" -> {
                return SWEDISH
            }
            "Swahili" -> {
                return SWAHILI
            }
            "Tagalog" -> {
                return TAGALOG
            }
            "Tamil" -> {
                return TAMIL
            }
            "Telugu" -> {
                return TELUGU
            }
            "Thai" -> {
                return THAI
            }
            "Turkish" -> {
                return TURKISH
            }
            "Ukrainian" -> {
                return UKRAINIAN
            }
            "Urdu" -> {
                return URDU
            }
            "Vietnamese" -> {
                return VIETNAMESE
            }
            "Welsh" -> {
                return WELSH
            }
        }
        return "Language not found"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}