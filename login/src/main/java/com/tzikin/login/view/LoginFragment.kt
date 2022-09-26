package com.tzikin.login.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.tzikin.core.BaseFragment
import com.tzikin.core.common.ConstantsDeepLink
import com.tzikin.core.common.toast
import com.tzikin.core.helpers.RequestState
import com.tzikin.home.news.viewmodel.NewsViewModel
import com.tzikin.login.R
import com.tzikin.login.databinding.FragmentLoginBinding
import com.tzikin.login.viewmodel.LoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_login

    private val viewModel: LoginViewModel by viewModels()
    private val homeViewModel: NewsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.loginButton.onClickListener {
            viewModel.login().observe(requireActivity()){
                it?.let {
                    when (it) {
                        is RequestState.Success -> {
                            dismissProgressBar()
                            navigateTo(ConstantsDeepLink.HOME_PAGE_DEEP_LINK)
                        }

                        is RequestState.loading -> {
                            showProgressBar()
                        }

                        is RequestState.Error -> {
                            dismissProgressBar()
                            requireActivity().toast(it.message)
                        }
                    }
                }
            }
        }

        binding.txtSignUp.setOnClickListener {
            navigateTo(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.emailText.getEmailInput().doOnTextChanged { text, _, _, _ ->
            viewModel.setEmail(text.toString())
        }

        binding.passwordText.getPasswordInput().doOnTextChanged { text, _, _, _ ->
            viewModel.setPassword(text.toString())
        }
    }
}