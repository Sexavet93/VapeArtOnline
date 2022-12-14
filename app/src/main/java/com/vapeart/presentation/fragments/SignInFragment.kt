package com.vapeart.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.vapeart.R
import com.vapeart.databinding.FragmentSignInBinding
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.utils.ViewsManager
import com.vapeart.presentation.viewmodels.SignInFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("Field binding in SignInFragment == null")
    private lateinit var navigator: Navigator
    private lateinit var viewsManager: ViewsManager
    private lateinit var email: String
    private var password: String = ""
    private val arguments: SignInFragmentArgs by navArgs()
    private val viewModel: SignInFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        email = arguments.email
        navigator = requireActivity() as Navigator
        viewsManager = requireActivity() as ViewsManager
        viewsManager.toolBarAndBottomNavigationBarVisibility(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEmailEditText()
        setCreateNewAccButtonListener()
        setSignInButtonListener()
        registerViewModelObservers()
        setForgotPasswordButtonListener()
    }
    
    private fun registerViewModelObservers(){
        viewModel.isSuccess.observe(viewLifecycleOwner){
            if(it){
                navigator.navigate(SignInFragmentDirections.actionSignInFragmentToHomeFragment())
                viewsManager.toolBarAndBottomNavigationBarVisibility(true)
                viewsManager.drawerLayoutLock(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }

        viewModel.exceptionMessage.observe(viewLifecycleOwner){ message ->
            if(message.isNotEmpty()) showToast(message)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setEmailEditText(){
        if(email.isNotBlank()) binding.emailEditText.setText(email)
    }

    private fun setCreateNewAccButtonListener(){
        binding.createNewAccButton.setOnClickListener{
            navigator.navigate(SignInFragmentDirections
                .actionSignInFragmentToSignUpFragment(email))
        }
    }

    private fun showWarning(){
        if(!viewModel.isEmailValidate(email)) binding.emailEditText
            .error = getString(R.string.wrong_email)
        if(!viewModel.isPasswordValidate(password)) binding.passwordEditText
            .error = getString(R.string.wrong_password)
    }

    private fun setSignInButtonListener(){
        binding.signInButton.setOnClickListener {
            email = binding.emailEditText.text.toString()
            password = binding.passwordEditText.text.toString()
            if(viewModel.signIn(email,password))
                binding.progressBar.visibility = View.VISIBLE
            else showWarning()
        }
    }

    private fun setForgotPasswordButtonListener(){
        binding.forgotPassword.setOnClickListener{
            email = binding.emailEditText.text.toString()
            navigator.navigate(SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment(email))
        }
    }

    private fun showToast(expression: String){
        Toast.makeText(requireContext(), expression, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}