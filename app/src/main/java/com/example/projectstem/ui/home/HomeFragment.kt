package com.example.projectstem.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectstem.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!! // This property is only valid between onCreateView and onDestroyView

    /**
     * This function is called by Android when the fragment should inflate a view.
     * It ensures that the fragment's root view is non-null.
     * @param inflater creates an instance of the binding class for the fragment to use
     * @param container is a view to contain other views, its classes are layouts
     * @param savedInstanceState is a reference to a Bundle object
     * @property onCreateView the name of this override function
     * @constructor View binding generates a binding class for each XML layout file present in the module
     * @return root
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    /**
     * This function allows the fragment to clean up resources associated with its View
     * @property onDestroyView the name of this override function
     * @constructor super is used inside a subclass method definition to call a method defined in the super class
     * @constructor binding is set to be null
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}