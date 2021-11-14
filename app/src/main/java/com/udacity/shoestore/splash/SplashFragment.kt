package com.udacity.shoestore.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.core.utils.SHARED_PREFERENCE_KEEP_LOGGED
import com.udacity.shoestore.core.utils.getSharedPreferenceBoolean
import com.udacity.shoestore.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater)
        setObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() {
        viewModel.endTimerEvent.observe(viewLifecycleOwner) {
            if (requireContext().getSharedPreferenceBoolean(SHARED_PREFERENCE_KEEP_LOGGED)) {
                findNavController().navigate(SplashFragmentDirections.toWelcomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.toLoginFragment())
            }
        }
    }
}
