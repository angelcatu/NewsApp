package com.tzikin.login.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseUser
import com.tzikin.core.helpers.RequestState
import com.tzikin.core.repository.login.AuthAppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel(application: Application) : AndroidViewModel(application) {


    private var authAppRepository: AuthAppRepository = AuthAppRepository()
    lateinit var requestState: MutableLiveData<RequestState<FirebaseUser?>>

    private val _email = MutableLiveData("")
    private val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    private val password: LiveData<String> = _password


    fun login() {

        viewModelScope.launch {
            requestState = authAppRepository.login(email = email.value.toString(), password = password.value.toString())
        }
    }


    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }
}