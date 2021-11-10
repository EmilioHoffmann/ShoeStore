package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setObservers()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() {
        viewModel.isEmailValid.observe(viewLifecycleOwner) {
            binding.emailField.error = if (it) null else getString(R.string.invalid_field_error)
        }
        viewModel.isPasswordValid.observe(viewLifecycleOwner) {
            binding.passwordField.error = if (it) null else getString(R.string.invalid_field_error)
        }
        viewModel.shouldNavigate.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate)
                findNavController().navigate(LoginFragmentDirections.toWelcomeFragment())
        }
    }
}
