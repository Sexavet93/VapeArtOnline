package com.vapeart.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.vapeart.R
import com.vapeart.presentation.activities.MainActivity
import com.vapeart.presentation.utils.Navigator
import com.vapeart.presentation.viewmodels.ForgotPasswordFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private lateinit var forgotPasswordEditText: EditText
    private lateinit var submitButton: Button
    private val viewModel: ForgotPasswordFragmentViewModel by viewModels()
    private lateinit var navigator: Navigator
    private lateinit var email: String
    private val arguments: ForgotPasswordFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        navigator = requireActivity() as MainActivity
        email = arguments.email
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forgotPasswordEditText = view.findViewById(R.id.forgotPasswordEditTExt)
        submitButton = view.findViewById(R.id.submitButton)
        forgotPasswordEditText.setText(email)
        registerViewModelObservers()
        setSubmitButtonListener()
    }

    private fun registerViewModelObservers(){
        viewModel.isSuccess.observe(viewLifecycleOwner){
            if(it){
                navigator.navigateUp()
                showToast(getString(R.string.check_email))
            }
        }

        viewModel.exceptionMessage.observe(viewLifecycleOwner){ message ->
            if(message.isNotEmpty()) showToast(message)
        }
    }

    private fun showToast(expression: String){
        Toast.makeText(requireContext(), expression, Toast.LENGTH_LONG).show()
    }

    private fun setSubmitButtonListener(){
        submitButton.setOnClickListener{
            val email = forgotPasswordEditText.text.toString()
            viewModel.sendPasswordResetEmail(email)
        }
    }

}