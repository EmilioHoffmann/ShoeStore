package com.udacity.shoestore.shoe_detail

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.shoe_list.ShoeListViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ShoeDetailFragment : Fragment() {

    private var _binding: FragmentShoeDetailBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: ShoeListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.descriptionEditorField.setRawInputType(InputType.TYPE_CLASS_TEXT)
        setListeners()
        setObservers()
        return binding.root
    }

    private fun setListeners() {
        binding.descriptionEditorField.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                viewModel.validateNewShoe()
                return@setOnEditorActionListener false
            }
            true
        }

        binding.cancelNewShoeButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setObservers() {
        viewModel.shoeIsEmptyWarning.observe(viewLifecycleOwner) {
            if (it) {
                needToEditFieldWarning()
            } else {
                findNavController().navigate(ShoeDetailFragmentDirections.toShoeListFragment())
            }
        }
    }

    private fun needToEditFieldWarning() {
        Toast.makeText(
            requireContext(),
            getString(R.string.need_to_edit_field_warning),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
