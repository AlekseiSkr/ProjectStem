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
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage.*
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslateFragment : Fragment() {

    private lateinit var notificationsViewModel: TranslateViewModel
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

        val textView: TextView = binding.textTranslate

        notificationsViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        //Drop down menu of languages - From
        val spinnerFrom: Spinner = root.findViewById(R.id.spinnerFrom)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lCategory,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFrom.adapter = adapter
        }
        //Drop down menu of languages - To
        val spinnerTo: Spinner = root.findViewById(R.id.spinnerTo)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.lCategory,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTo.adapter = adapter
        }

        val button = root.findViewById<Button>(R.id.buttonTranslate)
        val translateFromField = root.findViewById<EditText>(R.id.translateFrom)
        val translateToField = root.findViewById<EditText>(R.id.translateTo)
        button.setOnClickListener {
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
            "Arabic" -> {
                return ARABIC
            }
            "Belarusian" -> {
                return BELARUSIAN
            }
        }
        return "Language not found"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}