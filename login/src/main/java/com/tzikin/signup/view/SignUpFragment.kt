package com.tzikin.signup.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.tzikin.core.BaseFragment
import com.tzikin.core.common.toast
import com.tzikin.core.helpers.RequestState
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
                viewModel.registerUser().observe(requireActivity()) {
                    it?.let {
                        when(it){
                            is RequestState.loading -> {
                                showProgressBar()
                            }

                            is RequestState.Success -> {
                                dismissProgressBar()
                                requireActivity().toast(getString(com.tzikin.core.R.string.txt_user_created))
                                navigateTo(R.id.action_signUpFragment_to_loginFragment)
                            }

                            is RequestState.Error -> {
                                dismissProgressBar()
                                requireActivity().toast(it.message)
                            }
                        }
                    }
                }
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