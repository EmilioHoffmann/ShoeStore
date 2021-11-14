package com.udacity.shoestore.shoe_list

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.core.custom_views.ShoeView
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.shoe_list.model.Shoe
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: ShoeListViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeListBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        setObservers()
        setListeners()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        requireActivity().invalidateOptionsMenu()
    }

    private fun setObservers() {
        viewModel.shoeList.observe(viewLifecycleOwner) {
            if (it?.isNotEmpty() == true) {
                addNewShoeToViewList(it)
            }
        }
    }

    private fun setListeners() {
        binding.addShoeButton.setOnClickListener {
            viewModel.shoe = Shoe()
            findNavController().navigate(ShoeListFragmentDirections.toShoeDetailFragment())
        }
    }

    private fun addNewShoeToViewList(shoe: ArrayList<Shoe>) {
        shoe.forEach { item ->
            val newShoeView = ShoeView(binding.shoeListContainer.context, null)
            newShoeView.setupView(item)
            binding.shoeListContainer.addView(newShoeView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
