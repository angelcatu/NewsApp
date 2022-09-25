package com.tzikin.signup.view

import android.os.Bundle
import android.view.View
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

                navigateTo(R.id.action_signUpFragment_to_loginFragment)
            }
        }
    }

    private fun gettingInputValues() {
        binding.emailText.getEmailInput().doOnTextChanged { text, _, _, _ ->
            viewModel.setEmail(text.toString())
        }

        binding.passwordText.getPasswordInput().doOnTextChanged { text, _, _, _ ->
            viewModel.setPassword(text.toString())
        }

        binding.confirmationPasswordText.getPasswordInput().doOnTextChanged { text, _, _, _ ->
            viewModel.setConfirmationPassword(text.toString())
        }
    }

}