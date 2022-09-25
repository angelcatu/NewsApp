package com.tzikin.login.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.tzikin.core.BaseFragment
import com.tzikin.login.R
import com.tzikin.login.databinding.FragmentLoginBinding
import com.tzikin.login.viewmodel.LoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_login

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.loginButton.onClickListener {

        }

        binding.txtSignUp.setOnClickListener{
            navigateTo(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}