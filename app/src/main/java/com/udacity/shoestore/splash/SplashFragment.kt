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

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater)
        viewModel = SplashViewModel()
        setObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() {
        viewModel.endTimerEvent.observe(viewLifecycleOwner) {
            if (getSharedPreferenceBoolean(SHARED_PREFERENCE_KEEP_LOGGED)) {
                findNavController().navigate(SplashFragmentDirections.toWelcomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.toLoginFragment())
            }
        }
    }
}
