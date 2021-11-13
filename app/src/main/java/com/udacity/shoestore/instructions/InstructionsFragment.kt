package com.udacity.shoestore.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.core.utils.SHARED_ELEMENT_ADD_BUTTON
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setListeners()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.instructionsButton.setOnClickListener {
            val extras =
                FragmentNavigatorExtras(binding.addShoeButtonSample to SHARED_ELEMENT_ADD_BUTTON)
            findNavController().navigate(
                InstructionsFragmentDirections.toShoeListFragment(),
                extras
            )
        }
    }
}
