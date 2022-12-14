package com.vapeart.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.vapeart.R
import com.vapeart.databinding.FragmentSignUpBinding
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.viewmodels.SignUpFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding
        get() = _binding ?: throw RuntimeException("Field binding in SignUpFragment == null")
    private lateinit var email: String
    private var password: String = ""
    private var confirmPassword: String = ""
    private val arguments: SignUpFragmentArgs by navArgs()
    private val viewModel: SignUpFragmentViewModel by viewModels()
    private lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        email = arguments.email
        navigator = requireActivity() as Navigator
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEmailEditText()
        setSignUpButtonListener()
        registerViewModelObservers()
        setSignInButton()
    }

    private fun setEmailEditText(){
        if(email.isNotBlank()) binding.emailEditText.setText(email)
    }

    private fun setSignUpButtonListener(){
        binding.signUpButton.setOnClickListener {
            binding.apply {
                email = emailEditText.text.toString()
                password = passwordEditText.text.toString()
                confirmPassword = confirmPasswordEditText.text.toString()
            }
            if(viewModel.signUp(email,password,confirmPassword))
                binding.progressBar.visibility = View.VISIBLE
            else showWarning()
        }
    }

    private fun registerViewModelObservers(){
        viewModel.isSuccess.observe(viewLifecycleOwner){ isSuccess ->
            if(isSuccess){
                showToast(getString(R.string.registration_is_successful))
                navigator
                    .navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment(email))
            }

            viewModel.exceptionMessage.observe(viewLifecycleOwner){ message ->
                if(message.isNotEmpty()) showToast(message)
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun setSignInButton(){
        binding.signInTextView.setOnClickListener{
            navigator.navigateUp()
        }
    }

    private fun showToast(expression: String){
        Toast.makeText(requireContext(), expression, Toast.LENGTH_LONG).show()
    }

    private fun showWarning(){
        if(!viewModel.isEmailValidate(email)) binding.emailEditText
            .error = getString(R.string.wrong_email)
        if(!viewModel.isPasswordValidate(password)) binding.passwordEditText
            .error = getString(R.string.wrong_password)
        if(!viewModel.isConfirmPasswordValidate(password,confirmPassword))
            binding.confirmPasswordEditText.error = getString(R.string.wrong_confirm_password)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}