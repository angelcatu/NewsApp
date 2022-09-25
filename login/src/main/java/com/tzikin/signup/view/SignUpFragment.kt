package com.tzikin.signup.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.tzikin.core.BaseFragment
import com.tzikin.login.R
import com.tzikin.login.databinding.FragmentSignUpBinding
import com.tzikin.signup.viewmodel.SignUpViewModel


class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {


    override val layoutId: Int
        get() = R.layout.fragment_sign_up

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gettingInputValues()

        listeners()
    }

    private fun listeners() {
        binding.confirmButton.onClickListener{
            binding.errorLabel.text = viewModel.getErrorFromInputs()


            if(viewModel.allValuesCorrect.value == true){
                viewModel.registerUser()
            }
        }
    }

    private fun gettingInputValues() {
        binding.emailText.getEmailInput().doOnTextChanged { text, start, before, count ->
            viewModel.setEmail(text.toString())
        }

        binding.passwordText.getPasswordInput().doOnTextChanged { text, start, before, count ->
            viewModel.setPassword(text.toString())
        }

        binding.confirmationPasswordText.getPasswordInput().doOnTextChanged { text, start, before, count ->
            viewModel.setConfirmationPassword(text.toString())
        }
    }

}