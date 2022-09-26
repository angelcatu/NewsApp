package com.tzikin.login.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseUser
import com.tzikin.core.helpers.RequestState
import com.tzikin.core.repository.login.AuthAppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel(application: Application) : AndroidViewModel(application) {


    private var authAppRepository: AuthAppRepository = AuthAppRepository()

    private val _email = MutableLiveData("")
    private val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    private val password: LiveData<String> = _password


    fun login() =

        liveData(Dispatchers.IO) {
            emit(RequestState.loading)

            try {

                if(!email.value.isNullOrEmpty() && !password.value.isNullOrEmpty()){
                    emit(RequestState.Success(authAppRepository.login(email = email.value.toString(), password.value.toString())))
                }else{
                    emit(RequestState.Error("Insert email and password"))
                }
            }catch (e: Exception) {
                emit(RequestState.Error("User not found!"))
            }

        }


    fun setEmail(value: String) {
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }
}