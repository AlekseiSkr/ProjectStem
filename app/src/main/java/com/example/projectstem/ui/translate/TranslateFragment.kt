package com.example.projectstem.ui.translate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.projectstem.R
import com.example.projectstem.databinding.FragmentTranslateBinding
import com.example.projectstem.model.group.GroupViewModel
import com.example.projectstem.model.word.WordViewModel
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage.*
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

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


        //Drop down menu of languages - From
        val spinnerFrom: Spinner = root.findViewById(R.id.spinnerFrom)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lCategory,
            R.layout.drop_down
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinnerFrom.adapter = adapter
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
        }


        /*
database coding
 */

        //Insert word to group

        val saveButton = root.findViewById<Button>(R.id.buttonSaveTranslation)
        saveButton.visibility = View.VISIBLE
        val button = root.findViewById<Button>(R.id.buttonTranslate)

        val translateFromField = root.findViewById<EditText>(R.id.translateFrom)
        val translateToField = root.findViewById<EditText>(R.id.translateTo)

        button.setOnClickListener {
            //saveButton.visibility = View.INVISIBLE
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
            translator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener {
                    //Success downloading model. Continue to translation
                    translator.translate(wordToBeTranslated)
                        .addOnSuccessListener { translatedText ->
                            translateToField.setText(translatedText)

                            //If a word has been translated


                            ///Add data to database
                            wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
                            groupViewModel = ViewModelProvider(this).get(GroupViewModel::class.java)

                            saveButton.setOnClickListener {
                                Log.d("adding", "button pressed")
                                if (translateFromField.toString()
                                        .isNotEmpty() && translateToField.toString().isNotEmpty()
                                ) {

                                    //languages
                                    val language1 = spinnerFrom.selectedItem.toString()
                                    val language2 = spinnerTo.selectedItem.toString()
                                    val sth = groupViewModel.isRowIsExist(language1, language2)
                                    Log.d("adding", "$sth")

                                    if (sth.equals(false)) {
                                        //GROUP DOES NOT EXIST
                                        Toast.makeText(requireContext(), "Language Group does not exist!", Toast.LENGTH_LONG).show()

                                        Log.d("adding", "GROUP DOES NOT EXIST")

                                    } else if (sth.equals(true)) {
                                        //GROUP EXISTS
                                        Log.d("adding", "GROUP EXISTS")

//                                        var isInserted = false;
//                                        //add data
//                                        if (!isInserted) {
//                                            wordViewModel.addWordsToGroup(
//                                                0,
//                                                translateFromField.toString(),
//                                                translateToField.toString(),
//                                                1
//                                            )
//                                            isInserted = true;
//                                        }
//                                        if (isInserted) {
//                                            Toast.makeText(requireContext(), "$translateFromField.toString() Successfully added to group $language1 / $language2", Toast.LENGTH_LONG).show()
//                                            Navigation.findNavController(root).navigate(R.id.navigation_library)
//                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Please Add words to translate",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }


                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.e("s", Log.getStackTraceString(exception))
                        }
                }
                .addOnFailureListener { exception ->
                    Log.e("s", Log.getStackTraceString(exception))
                }

        }
        return root
    }

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