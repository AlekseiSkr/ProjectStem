package com.example.projectstem.ui.translate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectstem.databinding.FragmentTranslateBinding

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
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(TranslateViewModel::class.java)

        _binding = FragmentTranslateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTranslate
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}